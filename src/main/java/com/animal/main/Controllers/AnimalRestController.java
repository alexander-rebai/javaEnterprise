package com.animal.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animal.main.Entity.Animal;
import com.animal.main.Service.AdminService;

@RestController
@RequestMapping("/api/animals")
public class AnimalRestController {

    @Autowired
    private AdminService animalService;

    @GetMapping("/gender/{gender}")
    public List<Animal> getAnimalsByGender(@PathVariable String gender) {
        return animalService.getAnimalsByGender(gender);
    }

    @GetMapping("/breed/{breed}")
    public List<Animal> getAnimalsByRace(@PathVariable String breed) {
        return animalService.getAnimalsByRace(breed);

    }

    @GetMapping("/notReserved")
    public List<Animal> getNotReservedAnimals() {
        return animalService.getAllAnimalsNotyetReserved(false);
    }
}