package com.animal.main.DAO;

import com.animal.main.Entity.Accommodation;
import com.animal.main.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccomodationRepo extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a " +
            "WHERE a.animal_id=:animal_id")
    List<Accommodation> getAccommodationByAnimal_id(@Param("animal_id") int animal_id);

    @Modifying
    @Query("delete from Accommodation a where a.animal_id = :animal_id")
    void DeleteAccomodationByAnimalId(@Param("animal_id") int animal_id);

}
