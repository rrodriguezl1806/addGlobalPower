package com.example.addGlobalPower.controllers;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
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

import javax.validation.Valid;

import com.example.addGlobalPower.entities.User;
import com.example.addGlobalPower.services.UserService;

@RestController
@RequestMapping("/users")
class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// Get user by id
	@GetMapping("/{userId}")
	User userById(@PathVariable Long userId) {
		return userService.getUserById(userId);
		// try {
		// 	return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
		// } catch (Exception e) {
		// 	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		// }
	}

	// Create new user
	@PostMapping("")
	ResponseEntity<Object> newUser(@Valid @RequestBody User newUser) {
		try {
			return new ResponseEntity<>(userService.createUser(newUser), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Update user
	@PutMapping("/{userId}")
	ResponseEntity<Object> updateUser(@RequestBody User newUser, @PathVariable Long userId) {
		return new ResponseEntity<>(userService.updateUser(newUser, userId), HttpStatus.OK);
	}

	// Delete a user
	@DeleteMapping("/{userId}")
	ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
	}

	// Like product
	@PostMapping("{userId}/product/{productId}/userLike")
	ResponseEntity<Object> likeProduct(@PathVariable Long userId, @PathVariable Long productId) {
		userService.likeProduct(userId, productId);
		return ResponseEntity.ok().build();
	}

	// Buy product
	@PostMapping("{userId}/product/{productId}/buy")
	ResponseEntity<Object> boughtProduct(@PathVariable Long userId, @PathVariable Long productId) {
		try {
			userService.buyProduct(userId, productId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// Get all products given userId
	@GetMapping("/{userId}/products")
	ResponseEntity<Object> getProductsByUser(@PathVariable Long userId) {
		return new ResponseEntity<>(userService.getProductsByUser(userId), HttpStatus.OK);
	}
}
