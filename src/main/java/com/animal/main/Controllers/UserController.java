package com.animal.main.Controllers;

import com.animal.main.Entity.Accommodation;
import com.animal.main.Entity.Animal;
import com.animal.main.Entity.User;
import com.animal.main.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private User CurrentUser = null;

    @GetMapping("/")
    public String welcomeUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String CurrenLoggedUsername = auth.getName();
        CurrentUser = userService.getUserByUsername(CurrenLoggedUsername);

        List<Animal> list = userService.getAllAnimals();

        List<Animal> sortedAnimals = list.stream()
                .sorted(Comparator.comparing(Animal::getBreed)
                        .thenComparing(Comparator.comparing(Animal::getDob)))
                .collect(Collectors.toList());

        model.addAttribute("LoggedUser", CurrentUser);
        model.addAttribute("All_animals", sortedAnimals);
        return "user/welcomeUser";
    }

    @GetMapping("/animal_details")
    public String animal_details() {
        return "user/animal_details";
    }

    @RequestMapping("/view_accomodation/{id}")
    public String getAccommodationByAnimal_id(@PathVariable("id") int animal_id, Model model) {
        List<Accommodation> list = userService.getAccommodationByAnimal_id(animal_id);
        model.addAttribute("LoggedUser", CurrentUser);

        model.addAttribute("getAccomodationByAnimalId", list);
        return "user/view_accomodation";
    }

    @RequestMapping(value = "/animal_details/{id}", method = RequestMethod.GET)
    public String animal_details(@PathVariable("id") int id, Model model) {
        Animal list = userService.getAnimalsById(id);
        model.addAttribute("LoggedUser", CurrentUser);

        model.addAttribute("getOneAnimal", list);
        return "user/animal_details";
    }

    @RequestMapping("/reserved_animal/{user_id}/{animal_id}")
    public String reserved_animal(@PathVariable("user_id") int user_id, @PathVariable("animal_id") int animal_id,
            Model model, RedirectAttributes redirectAttributes) {
        System.out.println("USER ID: " + user_id);
        System.out.println("Animal ID: " + animal_id);

        List<Animal> reservedAnimals = userService.getAllReservedAnimalsByUser(user_id);

        if ((!CurrentUser.getAsielFan() && CurrentUser.getId() == user_id && reservedAnimals.size() < 1) ||
                (CurrentUser.getAsielFan() && CurrentUser.getId() == user_id && reservedAnimals.size() < 2)) {
            Animal animal = userService.getAnimalsById(animal_id);

            animal.setUser_reserved_id(user_id);
            animal.setReserved(true);

            model.addAttribute("LoggedUser", CurrentUser);

            userService.updateAnimal(animal);
        } else {
            redirectAttributes.addFlashAttribute("reservationError", "You cannot reserve more animals.");
        }
        return "redirect:/user/reserved_page/" + user_id;
    }

    @RequestMapping("/reserved_page/{user_id}")
    public String reserved_page(@PathVariable("user_id") int user_id, Model model) {

        if (CurrentUser.getId() == user_id) {

            List<Animal> animal = userService.getAllReservedAnimalsByUser(user_id);

            model.addAttribute("getAllReservedAnimalsByUser", animal);
            model.addAttribute("LoggedUser", CurrentUser);

        }

        return "user/reserved_animals";
    }

    @RequestMapping("/release_animal/{user_id}/{id}")
    public String release_animal(@PathVariable("user_id") int user_id, @PathVariable("id") int animal_id) {
        if (CurrentUser.getId() == user_id) {

            Animal animal = userService.getAnimalsById(animal_id);

            animal.setReserved(false);
            animal.setUser_reserved_id(0);

            userService.updateAnimal(animal);

        }
        return "redirect:/user/reserved_page/" + user_id;
    }

}