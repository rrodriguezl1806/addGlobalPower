package com.example.addGlobalPower.controllers;

import java.util.List;
import java.util.function.Function;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.addGlobalPower.entities.Product;
import com.example.addGlobalPower.entities.User;
import com.example.addGlobalPower.repositories.UserRepository;
import com.example.exception.UserNotFoundException;

@RestController
@RequestMapping("/users")
class UserController {

	private final UserRepository repository;

	UserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	List<User> all() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	User userById(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	// @GetMapping("/userProducts/{id}")
	// Product userProducts(@PathVariable Long id) {
	// 	return repository.findById(id)
	// 		.map(user -> {
	// 			return user.getProductUser();
	// 		});
	// }

	@PostMapping("")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}

	@PutMapping("/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return repository.findById(id)
			.map(user -> {
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setEmail(newUser.getEmail());
				user.setPhone(newUser.getPhone());
				user.setCardNumber(newUser.getCardNumber());
				user.setRole(newUser.getRole());
				return repository.save(user);
			})
			.orElseGet(() -> {
				newUser.setId(id);
					return repository.save(newUser);
			});
		}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}



}
