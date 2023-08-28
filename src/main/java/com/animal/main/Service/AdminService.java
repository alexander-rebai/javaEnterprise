package com.animal.main.Service;

import com.animal.main.DAO.AccomodationRepo;
import com.animal.main.DAO.AnimalRepo;
import com.animal.main.DAO.UserRepo;
import com.animal.main.Entity.Accomodation;
import com.animal.main.Entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AccomodationRepo accomodationRepo;
    @Autowired
    private AnimalRepo animalRepo;
    @Autowired
    private UserRepo userRepo;

    public Animal saveAnimal(Animal animal) {
        return animalRepo.save(animal);
    }

    public List<Animal> getAllAnimals() {
        List<Animal> list = animalRepo.findAll();
        return list;
    }

    public Animal getAnimalsById(int id) {
        return animalRepo.getAnimalsById(id);
    }

    public List<Accomodation> getAccommodationByAnimal_id(int animal_id) {
        return accomodationRepo.getAccommodationByAnimal_id(animal_id);
    }

    public Accomodation saveAccomodation(Accomodation accomodation) {
        return accomodationRepo.save(accomodation);
    }

    public void deleteAccomodation(Long id) {
        accomodationRepo.deleteById(id);
    }

    public void deleteAnimal(int id) {
        animalRepo.deleteById(id);
    }

    public Animal IDF_CODE_already_exists(String identification_code) {
        return animalRepo.IDF_CODE_already_exists(identification_code);
    }

    public void DeleteAccomodationByAnimalId(int animal_id) {
        accomodationRepo.DeleteAccomodationByAnimalId(animal_id);
    }

    public List<Animal> getAllAnimalsByBreed(String breed) {
        return animalRepo.getAllAnimalsByBreed(breed);
    }

    public List<Animal> getAllAnimalsByDate(String dob) {
        return animalRepo.getAllAnimalsByDate(dob);
    }

    public List<Animal> getAllAnimalsByBreedAndDate(String breed, String dob) {
        return animalRepo.getAllAnimalsByBreedAndDate(breed, dob);
    }

    public List<Animal> getAllAnimalsReserved() {
        return animalRepo.getAllAnimalsNotReserved(true);
    }

    public Animal updateAnimal(Animal animal) {
        return animalRepo.save(animal);
    }

    public List<Animal> getAnimalsByGender(String gender) {
        return animalRepo.getAnimalsByGender(gender);
    }

    public List<Animal> getAnimalsByRace(String breed) {
        return animalRepo.getAnimalsByRace(breed);
    }

    public List<Animal> getAllAnimalsNotyetReserved(boolean reserved) {
        return animalRepo.getAllAnimalsNotyetReserved(reserved);
    }
}
