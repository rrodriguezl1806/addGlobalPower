package com.example.addGlobalPower.controllers;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.addGlobalPower.entities.Product;
import com.example.addGlobalPower.repositories.ProductRepository;
import com.example.addGlobalPower.services.ProductService;
import com.example.addGlobalPower.specifications.ProductSpecificationsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	private ProductRepository productRepository;
	
	ProductController(ProductService productService, ProductRepository productRepository) {
		this.productService = productService;
		this.productRepository = productRepository;
	}
	
	// Get all Products
	// @GetMapping("")
	// ResponseEntity<Object> allProducts() {
	// 	return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	// }

	@GetMapping("")
	List<Product> search(@RequestParam(value = "search") String search) {
		ProductSpecificationsBuilder builder = new ProductSpecificationsBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}

		Specification<Product> spec = builder.build();
		return productRepository.findAll(spec);
  }

	// @GetMapping("")
	// ResponseEntity<Object> products(
	// 	@RequestParam(defaultValue = "all") String category, 
	// 	@RequestParam(defaultValue = "0") Integer minPrice, 
	// 	@RequestParam(defaultValue = "1000") Integer maxPrice) {
	// 	return new ResponseEntity<>(productService.getProducts(category, minPrice, maxPrice), HttpStatus.OK);
	// }

	// Get Product by productId
	@GetMapping("/{id}")
	ResponseEntity<Object> productById(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
	}
	
	// Create new Product
	@PostMapping("")
	ResponseEntity<Object> newProduct(@RequestBody Product newProduct) {
		return new ResponseEntity<>(productService.createProduct(newProduct), HttpStatus.OK);
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
