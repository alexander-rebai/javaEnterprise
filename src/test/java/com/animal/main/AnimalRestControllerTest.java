package com.animal.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.animal.main.Controllers.AnimalRestController;
import com.animal.main.DAO.UserRepo;
import com.animal.main.Entity.Animal;
import com.animal.main.Service.AdminService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalRestController.class)
public class AnimalRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService animalService;

    @MockBean
    private UserRepo userRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public static Animal createBasicAnimal(String name) {
        Animal animal = new Animal();
        animal.setName(name);

        return animal;
    }

    @Test
    public void testGetAnimalsByGender() throws Exception {
        List<Animal> animals = new ArrayList<>();
        animals.add(createBasicAnimal("Cat"));
        animals.add(createBasicAnimal("Dog"));

        when(animalService.getAnimalsByGender("Female")).thenReturn(animals);

        mockMvc.perform(get("/api/animals/gender/Female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cat"))
                .andExpect(jsonPath("$[1].name").value("Dog"));
    }

    @Test
    public void testGetAnimalsByBreed() throws Exception {
        List<Animal> animals = new ArrayList<>();
        animals.add(createBasicAnimal("Cat"));
        animals.add(createBasicAnimal("Dog"));

        when(animalService.getAnimalsByRace("Cat")).thenReturn(animals);

        mockMvc.perform(get("/api/animals/breed/Cat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cat"))
                .andExpect(jsonPath("$[1].name").value("Dog"));
    }

    @Test
    public void testGetNotReservedAnimals() throws Exception {
        List<Animal> animals = new ArrayList<>();
        animals.add(createBasicAnimal("Cat"));
        animals.add(createBasicAnimal("Dog"));

        when(animalService.getAllAnimalsNotyetReserved(false)).thenReturn(animals);

        mockMvc.perform(get("/api/animals/notReserved"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cat"))
                .andExpect(jsonPath("$[1].name").value("Dog"));
    }
}
