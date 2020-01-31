package com.example.addGlobalPower.repositories;

import com.example.addGlobalPower.entities.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductUserRepository extends JpaRepository<ProductUser, Long> {
}
