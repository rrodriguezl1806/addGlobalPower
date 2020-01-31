package com.example.addGlobalPower.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.addGlobalPower.dto.ProductDto;
import com.example.addGlobalPower.entities.Product;
import com.example.addGlobalPower.entities.ProductUser;
import com.example.addGlobalPower.repositories.ProductRepository;
import com.example.addGlobalPower.repositories.ProductUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.addGlobalPower.entities.User;
import com.example.addGlobalPower.repositories.UserRepository;
import com.example.exception.UserNotFoundException;

@RestController
@RequestMapping("/users")
class UserController {

	private final UserRepository userRepository;

	private final ProductRepository productRepository;

	private final ProductUserRepository productUserRepository;

	public UserController(UserRepository repository, ProductRepository productRepository, ProductUserRepository productUserRepository) {
		this.userRepository = repository;
		this.productRepository = productRepository;
		this.productUserRepository = productUserRepository;
	}

//	@GetMapping("")
//	List<User> all() {
//		return userRepository.findAll();
//	}

//	@GetMapping("/{id}")
//	User userById(@PathVariable Long id) {
//		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
//	}

	// @GetMapping("/userProducts/{id}")
	// Product userProducts(@PathVariable Long id) {
	// 	return repository.findById(id)
	// 		.map(user -> {
	// 			return user.getProductUser();
	// 		});
	// }

	@PostMapping("")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

	@PutMapping("/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepository.findById(id)
			.map(user -> {
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setEmail(newUser.getEmail());
				user.setPhone(newUser.getPhone());
				user.setCardNumber(newUser.getCardNumber());
				user.setRole(newUser.getRole());
				return userRepository.save(user);
			})
			.orElseGet(() -> {
				newUser.setId(id);
					return userRepository.save(newUser);
			});
		}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}

	@PostMapping("{userId}/product/{productId}")
	ResponseEntity addProduct(@PathVariable Long userId, @PathVariable Long productId){
		ProductUser productUser = new ProductUser();
		productUser.setProduct(productRepository.getOne(productId));
		productUser.setUser(userRepository.getOne(userId));
		productUserRepository.save(productUser);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}/products")
	List<ProductDto> getProducts (@PathVariable Long id){
		User user = userRepository.getOne(id);
		return user.getProductUser().stream().map(productUser -> {
			Product product = productUser.getProduct();
			return new ProductDto(product.getId()
					, product.getName()
					, product.getCategory()
					, product.getPrice()
					, product.getDescription()
					, product.getQuantityAvailable()
					, product.getAssessment());
		}).collect(Collectors.toList());
	}
}
