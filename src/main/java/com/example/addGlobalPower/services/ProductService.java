package com.example.addGlobalPower.services;

import java.util.List;

import com.example.addGlobalPower.entities.Product;
import com.example.addGlobalPower.repositories.ProductRepository;
import com.example.exception.product.*;
import org.springframework.stereotype.Service;

/**
 * Product Service
 */
@Service
public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(long id) {
    return productRepository.findById(id)
      .orElseThrow(() -> new ProductNotFoundException(id));
  }

  public Product createProduct(Product product) {
    List<Product> productList = productRepository.findByStockNumber(product.getStockNumber());
    if (productList.size() == 0) {
      return productRepository.save(product);
    } else {
      return product;
    }
  }

  public Product updateProduct(Product newProduct, long productId) {
    return productRepository.findById(productId)
			.map(product -> {
				product.setName(newProduct.getStockNumber());
				product.setName(newProduct.getName());
				product.setCategory(newProduct.getCategory());
				product.setPrice(newProduct.getPrice());
				product.setDescription(newProduct.getDescription());
				return productRepository.save(product);
		})
		.orElseGet(() -> {
			newProduct.setId(productId);
        return productRepository.save(newProduct);
		});
  }

  public void deleteProduct(long productId) {
    productRepository.deleteById(productId);
  }

  // Count Likes
  public Integer countLikes(long productId) {
    return productRepository.findById(productId).map(product -> {
			Integer likes = product.getLikes();
			return likes;
		}).orElseThrow(() -> new ProductNotFoundException(productId));
  }

  // Count Sold
  public Integer countSold(long productId) {
    return productRepository.findById(productId).map(product -> {
			Integer sold = product.getSold();
			return sold;
		}).orElseThrow(() -> new ProductNotFoundException(productId));
  }



  
}