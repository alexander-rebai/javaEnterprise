package com.animal.main.Service;

import com.animal.main.DAO.AccomodationRepo;
import com.animal.main.DAO.AnimalRepo;
import com.animal.main.DAO.UserRepo;
import com.animal.main.Entity.Accomodation;
import com.animal.main.Entity.Animal;
import com.animal.main.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private AccomodationRepo accomodationRepo;
    @Autowired
    private AnimalRepo animalRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Animal> getAllAnimals() {
        List<Animal> list = animalRepo.findAll();
        return list;
    }

    public List<Animal> getAllAnimalsNotReserved(boolean reserved) {
        List<Animal> list = animalRepo.getAllAnimalsNotReserved(reserved);
        return list;
    }

    public List<Accomodation> getAccommodationByAnimal_id(int animal_id) {
        return accomodationRepo.getAccommodationByAnimal_id(animal_id);
    }

    public Animal getAnimalsById(int id) {
        return animalRepo.getAnimalsById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public Animal updateAnimal(Animal animal) {
        return animalRepo.save(animal);
    }

    public List<Animal> getAllReservedAnimalsByUser(int user_id) {
        return animalRepo.getAllReservedAnimalsByUser(user_id);
    }

}
