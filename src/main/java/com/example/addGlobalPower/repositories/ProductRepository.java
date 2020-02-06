package com.example.addGlobalPower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.addGlobalPower.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  // List<Product> findByStockNumber(String stockNumber);
}