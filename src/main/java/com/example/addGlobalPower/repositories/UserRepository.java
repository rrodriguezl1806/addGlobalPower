package com.example.addGlobalPower.repositories;

import com.example.addGlobalPower.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  // @Query("SELECT u FROM User u WHERE u.email = :email")
  // List<User> findByEmail(String email);
}
