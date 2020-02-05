package com.example.addGlobalPower.controllers;

import org.springframework.http.HttpStatus;
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
import com.example.addGlobalPower.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	// Get all Products
	@GetMapping("")
	ResponseEntity<Object> allProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	// Get Product by productId
	@GetMapping("/{id}")
	ResponseEntity<Object> productById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	// Create new Product
	@PostMapping("")
	ResponseEntity<Object> newProduct(@RequestBody Product newProduct) {
		try {
			return new ResponseEntity<>(productService.createProduct(newProduct), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Update Product by productId
	@PutMapping("/{id}")
	ResponseEntity<Object> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		return new ResponseEntity<>(productService.updateProduct(newProduct, id), HttpStatus.OK);
	}

	// Delete Product by productId
	@DeleteMapping("/{id}")
	ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
	}

	// Count likes
	@GetMapping("{productId}/likes")
	ResponseEntity<Object> countLikes(@PathVariable final Long productId) {
		return new ResponseEntity<>(productService.countLikes(productId), HttpStatus.OK);
	}

	// Count sold
	@GetMapping("{productId}/sold")
	ResponseEntity<Object> countSold(@PathVariable final Long productId) {
		return new ResponseEntity<>(productService.countSold(productId), HttpStatus.OK);
	}
	
}
