package com.example.addGlobalPower.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.addGlobalPower.dto.ProductDto;
import com.example.addGlobalPower.entities.Product;
import com.example.addGlobalPower.entities.ProductUser;
import com.example.addGlobalPower.entities.User;
import com.example.addGlobalPower.repositories.ProductRepository;
import com.example.addGlobalPower.repositories.ProductUserRepository;
import com.example.addGlobalPower.repositories.UserRepository;
import com.example.exception.user.*;

import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

  private UserRepository userRepository;
  private ProductRepository productRepository;
  private ProductUserRepository productUserRepository;

  public UserService(
    UserRepository userRepository,
    ProductUserRepository productUserRepository,
    ProductRepository productRepository
  ) {
    this.userRepository = userRepository;
    this.productUserRepository = productUserRepository;
    this.productRepository = productRepository;
  }

  // Get user by id
  public User getUserById(long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException(userId));
  }

  // Create new user
  public User createUser(User newUser) {
		// List<User> user = userRepository.findByEmail(newUser.getEmail());
		// if (user.size() == 0) {
			return userRepository.save(newUser);
		// } else {
		// 	return newUser;
		// }
  }

  // Update user
  public User updateUser(User newUser, long userId) {
    return userRepository.findById(userId)
			.map(user -> {
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setEmail(newUser.getEmail());
				user.setPhone(newUser.getPhone());
				user.setAddress(newUser.getAddress());
				return userRepository.save(user);
			})
			.orElseGet(() -> {
				newUser.setId(userId);
					return userRepository.save(newUser);
			});
  }

  // Delete user
	public void deleteUser(long userId) {
		Optional<User> user = userRepository.findById(userId);
		user.ifPresent(value -> userRepository.delete(value));
	}

  // Get all products given userId
  public List<ProductDto> getProductsByUser(long userId) {
    User user = userRepository.getOne(userId);
		return user.getProductUser().stream().map(productUser -> {
			 Product product = productUser.getProduct();
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

  // Like product by user
  public void likeProduct(long userId, long productId) {
    List<ProductUser> productUserList = productUserRepository.findProductUserByUserIdAndProductId(userId, productId);
		if (productUserList.size() == 0) {
			ProductUser productUser = new ProductUser();
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
		}
  }

  // Buy product by user
  public void buyProduct(long userId, long productId) throws Exception{
    List<ProductUser> productUserList = productUserRepository.findProductUserByUserIdAndProductId(userId, productId);
		if (productUserList.size() == 0) {
			ProductUser productUser = new ProductUser();
			productUser.setProduct(productRepository.getOne(productId));
			productUser.setUser(userRepository.getOne(userId));
	
			Integer sold = productUser.getProduct().getSold();
			productUser.setBought(true);
			productUser.getProduct().setSold(sold + 1);
			productUserRepository.save(productUser);
		} else {
			throw	new Exception("User not Found in deleteUser");
		}
  }
}