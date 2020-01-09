package com.example.addGlobalPower.repositories;

import com.example.addGlobalPower.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
