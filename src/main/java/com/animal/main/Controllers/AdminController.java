package com.animal.main.Controllers;

import com.animal.main.Entity.Accommodation;
import com.animal.main.Entity.Animal;
import com.animal.main.Service.AdminService;
import com.animal.main.validator.IdentificationcodeValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private IdentificationcodeValidator identificationcodeValidator;

    @RequestMapping("/")
    public String welcomeUser(Model model) {
        List<Animal> list = adminService.getAllAnimals();

        List<Animal> sortedAnimals = list.stream()
                .sorted(Comparator.comparing(Animal::getBreed)
                        .thenComparing(Comparator.comparing(Animal::getDob)))
                .collect(Collectors.toList());

        model.addAttribute("All_animals", sortedAnimals);
        return "admin/welcomeAdmin";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam("breed") String breed, Model model) {
        List<Animal> list = null;

        if (breed != null && !breed.equals("")) {
            list = adminService.getAllAnimalsByBreed(breed);
        } else {
            list = adminService.getAllAnimals();
        }

        model.addAttribute("All_animals", list);
        return "admin/welcomeAdmin";
    }

    @RequestMapping(value = "/animal_details/{id}", method = RequestMethod.GET)
    public String animal_details(@PathVariable("id") int id, Model model) {
        Animal list = adminService.getAnimalsById(id);
        model.addAttribute("getOneAnimal", list);
        return "admin/animal_details";
    }

    @RequestMapping(value = "/add_animal_form", method = RequestMethod.POST)
    public String add_animal(@Valid Animal animal,
            BindingResult bindingResult,
            Model model) {

        // set empty checkbox values to false
        if (animal.getCan_be_used_with_older_children() == null) {
            animal.setCan_be_used_with_older_children(false);
        }
        if (animal.getCan_be_used_with_young_children() == null) {
            animal.setCan_be_used_with_young_children(false);
        }
        if (animal.getCan_with_cats() == null) {
            animal.setCan_with_cats(false);
        }
        if (animal.getCan_with_dogs() == null) {
            animal.setCan_with_dogs(false);
        }
        if (animal.getSuitable_as_an_indoor_cat() == null) {
            animal.setSuitable_as_an_indoor_cat(false);
        }

        identificationcodeValidator.validate(animal.getIdentification_code(), bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/add_animal"; // Return the form page with errors
        }

        if (adminService.IDF_CODE_already_exists(animal.getIdentification_code()) == null) {
            adminService.saveAnimal(animal);
        } else {
            model.addAttribute("errorIDF", "Identification Code Already Exists");
            return "admin/add_animal";
        }
        return "redirect:/admin/";
    }

    @RequestMapping("/view_accomodation/{id}")
    public String getAccomodationByAnimal_id(@PathVariable("id") int animal_id, Model model) {
        List<Accommodation> list = adminService.getAccommodationByAnimal_id(animal_id);
        model.addAttribute("getAccomodationByAnimalId", list);
        return "admin/view_accomodation";
    }

    @RequestMapping(value = "/add_accomodation", method = RequestMethod.POST)
    public String add_accomodation(@ModelAttribute("Accomodation") Accommodation accommodation) {
        adminService.saveAccomodation(accommodation);
        return "redirect:/admin/";
    }

    @RequestMapping("/deleteAccomodation/{id}")
    public String deleteAccomodation(@PathVariable("id") Long id) {
        adminService.deleteAccomodation(id);
        return "redirect:/admin/";
    }

    @RequestMapping("/delete_animal/{id}")
    public String delete_animal(@PathVariable("id") int id) {
        adminService.deleteAnimal(id);
        adminService.DeleteAccomodationByAnimalId(id);
        return "redirect:/admin/";
    }

    @RequestMapping("/reserved_animals")
    public String reserved_animals(Model model) {
        List<Animal> animals = adminService.getAllAnimalsReserved();
        model.addAttribute("getAllAnimalsReserved", animals);
        return "admin/reserved_animals";
    }

    @RequestMapping("/release_animal/{id}")
    public String release_animal(@PathVariable("id") int animal_id) {
        Animal animal = adminService.getAnimalsById(animal_id);

        animal.setReserved(false);
        animal.setUser_reserved_id(0);

        adminService.updateAnimal(animal);
        return "redirect:/admin/reserved_animals";
    }

    @RequestMapping("/add_animal")
    public String add_animal(Model model) {
        model.addAttribute("animal", new Animal());
        return "admin/add_animal";
    }
}
