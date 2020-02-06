package com.example.addGlobalPower.repositories;

import java.util.List;
import java.util.Optional;

import com.example.addGlobalPower.entities.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductUserRepository extends JpaRepository<ProductUser, Long> {
  
  @Query("SELECT u FROM ProductUser u WHERE u.product.id = :productId and u.user.id = :userId")
  List<ProductUser> findProductUserByUserIdAndProductId(
    @Param("userId") long userId, 
    @Param("productId") long productId);

  @Query("SELECT u FROM ProductUser u WHERE u.product.id = :productId and u.user.id = :userId and u.userLike = :userLike")
  List<ProductUser> findProductUserByUserIdAndProductIdAndUserLike(
    @Param("userId") long userId, 
    @Param("productId") long productId,
    @Param("userLike") boolean userLike);

  @Query("SELECT u FROM ProductUser u WHERE u.product.id = :productId and u.user.id = :userId and u.bought = :bought")
  List<ProductUser> findProductUserByUserIdAndProductIdAndBought(
    @Param("userId") long userId, 
    @Param("productId") long productId,
    @Param("bought") boolean bought);
}
