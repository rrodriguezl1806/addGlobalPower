package com.example.addGlobalPower.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.addGlobalPower.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}