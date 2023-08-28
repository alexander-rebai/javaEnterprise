package com.animal.main.DAO;

import com.animal.main.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("select u from User u " +
            "where u.username =:username")
    User getUserByUsername(@Param("username") String username);

}
