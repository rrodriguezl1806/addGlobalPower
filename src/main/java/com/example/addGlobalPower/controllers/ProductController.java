package com.example.addGlobalPower.controllers;

import java.util.List;

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

	private final ProductRepository repository;
	
	ProductController(ProductRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("")
	List<Product> all() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	Product productById(@PathVariable Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));
	}
	
	@PostMapping("")
	Product newProduct(@RequestBody Product newProduct) {
		return repository.save(newProduct);
	}

	@PutMapping("/{id}")
	Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		return repository.findById(id)
			.map(product -> {
				product.setName(newProduct.getName());
				product.setCategory(newProduct.getCategory());
				product.setPrice(newProduct.getPrice());
				product.setDescription(newProduct.getDescription());
				return repository.save(product);
		})
		.orElseGet(() -> {
			newProduct.setId(id);
        return repository.save(newProduct);
		});
	}

	@DeleteMapping("/{id}")
	void deleteProduct(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
