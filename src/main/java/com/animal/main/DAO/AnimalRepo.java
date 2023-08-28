package com.animal.main.DAO;

import com.animal.main.Entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepo extends JpaRepository<Animal, Integer> {

        @Query("select a from Animal a  " +
                        "where a.id = :id")
        Animal getAnimalsById(@Param("id") int id);

        @Query("select a from Animal a " +
                        "where a.identification_code =:identification_code")
        Animal IDF_CODE_already_exists(@Param("identification_code") String identification_code);

        @Query("select a from Animal a  " +
                        "where a.breed = :breed")
        List<Animal> getAllAnimalsByBreed(@Param("breed") String breed);

        @Query("select a from Animal a  " +
                        "where a.dob = :dob")
        List<Animal> getAllAnimalsByDate(@Param("dob") String dob);

        @Query("select a from Animal a  " +
                        "where a.breed = :breed And a.dob=:dob")
        List<Animal> getAllAnimalsByBreedAndDate(@Param("breed") String breed, @Param("dob") String dob);

        @Query("select a from Animal a  " +
                        "where a.reserved =:reserved")
        List<Animal> getAllAnimalsNotReserved(@Param("reserved") boolean reserved);

        @Query("select a from Animal a  " +
                        "where a.user_reserved_id =:user_reserved_id")
        List<Animal> getAllReservedAnimalsByUser(@Param("user_reserved_id") int user_reserved_id);

        @Query("select a from Animal a  " +
                        "where a.sex =:sex")
        List<Animal> getAnimalsByGender(@Param("sex") String sex);

        @Query("select a from Animal a  " +
                        "where a.race =:breed")
        List<Animal> getAnimalsByRace(@Param("breed") String breed);

        @Query("select a from Animal a  " +
                        "where a.reserved =:reserved")
        List<Animal> getAllAnimalsNotyetReserved(@Param("reserved") boolean reserved);

}
