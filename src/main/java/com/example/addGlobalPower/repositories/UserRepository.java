package com.example.addGlobalPower.repositories;

import com.example.addGlobalPower.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  // @Query("SELECT u FROM User u WHERE u.email = :email")
  // List<User> findByEmail(String email);

    List<User> findByUsername(@Param("username") String username);
}
