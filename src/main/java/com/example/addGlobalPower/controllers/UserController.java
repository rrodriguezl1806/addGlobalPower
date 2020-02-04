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

	private UserRepository userRepository;
	private ProductRepository productRepository;
	private ProductUserRepository productUserRepository;

	public UserController(final UserRepository repository, final ProductRepository productRepository, final ProductUserRepository productUserRepository) {
		this.userRepository = repository;
		this.productRepository = productRepository;
		this.productUserRepository = productUserRepository;
	}

	@GetMapping("/{id}")
	User userById(@PathVariable final Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PostMapping("")
	User newUser(@RequestBody final User newUser) {
		return userRepository.save(newUser);
	}

	@PutMapping("/{id}")
	User updateUser(@RequestBody final User newUser, @PathVariable final Long id) {
		return userRepository.findById(id)
			.map(user -> {
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setEmail(newUser.getEmail());
				user.setPhone(newUser.getPhone());
				user.setAddress(newUser.getAddress());
				return userRepository.save(user);
			})
			.orElseGet(() -> {
				newUser.setId(id);
					return userRepository.save(newUser);
			});
		}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable final Long id) {
		userRepository.deleteById(id);
	}

	// Like product
	@PostMapping("{userId}/product/{productId}/userLike")
	ResponseEntity likeProduct(@PathVariable final Long userId, @PathVariable final Long productId){
		final ProductUser productUser = new ProductUser();
		productUser.setProduct(productRepository.getOne(productId));
		productUser.setUser(userRepository.getOne(userId));
		boolean like = productUser.getUserLike();
		Integer likes = productUser.getProduct().getLikes();
		productUser.setUserLike(!like);
		if (!like) {
			productUser.getProduct().setLikes(likes + 1);
		} else {
			productUser.getProduct().setLikes(Math.decrementExact(likes - 1));
		}
		productUserRepository.save(productUser);
		return ResponseEntity.ok().build();
	}

	// buy product
	@PostMapping("{userId}/product/{productId}/buy")
	ResponseEntity boughtProduct(@PathVariable final Long userId, @PathVariable final Long productId){
		final ProductUser productUser = new ProductUser();
		productUser.setProduct(productRepository.getOne(productId));
		productUser.setUser(userRepository.getOne(userId));

		Integer sold = productUser.getProduct().getSold();
		productUser.setBought(true);
		productUser.getProduct().setSold(sold + 1);
		productUserRepository.save(productUser);
		return ResponseEntity.ok().build();
	}

	// Get all products given userId
	@GetMapping("/{id}/products")
	List<ProductDto> getProducts (@PathVariable final Long id){
		final User user = userRepository.getOne(id);
		return user.getProductUser().stream().map(productUser -> {
			final Product product = productUser.getProduct();
			return new ProductDto(
						product.getId()
					, product.getName()
					, product.getCategory()
					, product.getPrice()
					, product.getDescription()
					, product.getLikes()
					, product.getSold()
					, product.getPhoto()
					, productUser.getCreated_date()
					, productUser.getUserLike()
					, productUser.getBought());
		}).collect(Collectors.toList());
	}
}
