package com.example.addGlobalPower.repositories;

import java.util.List;

import com.example.addGlobalPower.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

  // @Query("SELECT u FROM User u WHERE u.email = :email")
  List<User> findByEmail(String email);
}
