package com.animal.main.DAO;

import com.animal.main.Entity.Accomodation;
import com.animal.main.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccomodationRepo extends JpaRepository<Accomodation, Long> {

    @Query("SELECT a FROM Accomodation a " +
            "WHERE a.animal_id=:animal_id")
    List<Accomodation> getAccommodationByAnimal_id(@Param("animal_id") int animal_id);

    @Modifying
    @Query("delete from Accomodation a where a.animal_id = :animal_id")
    void DeleteAccomodationByAnimalId(@Param("animal_id") int animal_id);

}
