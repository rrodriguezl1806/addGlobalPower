package com.example.addGlobalPower.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.addGlobalPower.entities.Product;
import com.example.addGlobalPower.repositories.ProductRepository;
import com.example.exception.ProductNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductRepository productRepository;
	
	ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping("")
	List<Product> all() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	Product productById(@PathVariable Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));
	}
	
	@PostMapping("")
	Product newProduct(@RequestBody Product newProduct) {
		return productRepository.save(newProduct);
	}

	@PutMapping("/{id}")
	Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		return productRepository.findById(id)
			.map(product -> {
				product.setName(newProduct.getName());
				product.setCategory(newProduct.getCategory());
				product.setPrice(newProduct.getPrice());
				product.setDescription(newProduct.getDescription());
				return productRepository.save(product);
		})
		.orElseGet(() -> {
			newProduct.setId(id);
        return productRepository.save(newProduct);
		});
	}

	@DeleteMapping("/{id}")
	void deleteProduct(@PathVariable Long id) {
		productRepository.deleteById(id);
	}

	// Count likes
	@GetMapping("{productId}/likes")
	Integer countLikes(@PathVariable final Long productId) {
		return productRepository.findById(productId).map(product -> {
			Integer likes = product.getLikes();
			return likes;
		}).orElseThrow(() -> new ProductNotFoundException(productId));
	}

	// Count sold
	@GetMapping("{productId}/sold")
	Integer countSold(@PathVariable final Long productId) {
		return productRepository.findById(productId).map(product -> {
			Integer sold = product.getSold();
			return sold;
		}).orElseThrow(() -> new ProductNotFoundException(productId));
	}
	
}
