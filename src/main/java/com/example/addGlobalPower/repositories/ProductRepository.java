package com.example.addGlobalPower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.example.addGlobalPower.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

  // List<Product> findByStockNumber(String stockNumber);

  // @Query("SELECT p FROM Product p WHERE p.category = :category")
  List<Product> findProductByCategoryOrderBySoldAsc(String category);

  @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
  List<Product> findProductByPriceOrderByPriceAsc(
    @Param("minPrice") Integer minPrice,
    @Param("maxPrice") Integer maxPrice);

  @Query("SELECT p FROM Product p WHERE p.category = :category AND p.price BETWEEN :minPrice AND :maxPrice")
  List<Product> findProductByCategoryAndPriceOrderBySoldAsc(
    @Param("category") String category,
    @Param("minPrice") Integer minPrice,
    @Param("maxPrice") Integer maxPrice);
}