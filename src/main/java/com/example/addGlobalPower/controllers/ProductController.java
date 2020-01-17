package com.example.addGlobalPower.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.addGlobalPower.entities.Product;
import com.example.addGlobalPower.repositories.ProductRepository;

@RestController
public class ProductController {

	private final ProductRepository repository;
	
	ProductController(ProductRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/products")
	List<Product> all() {
		return repository.findAll();
	}
	
	@PostMapping("/product")
	Product newProduct(@RequestBody Product newProduct) {
		return repository.save(newProduct);
	}
	
}
