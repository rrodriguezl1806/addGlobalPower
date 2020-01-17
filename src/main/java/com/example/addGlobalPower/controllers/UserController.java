package com.example.addGlobalPower.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.addGlobalPower.entities.User;
import com.example.addGlobalPower.repositories.UserRepository;

@RestController
class UserController {
	
	private final UserRepository repository;
	
	UserController(UserRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/users")
	  List<User> all() {
	    return repository.findAll();
	  }
	
	@PostMapping("/user")
	  User newEmployee(@RequestBody User newUser) {
	    return repository.save(newUser);
	  }

}
