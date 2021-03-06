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
import com.example.addGlobalPower.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service(value = "userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductUserRepository productUserRepository;

	/**
	 * Get user by id
	 * @param userId userId
	 * @return User
	 */
	  public User getUserById(long userId) {
			return userRepository.findById(userId)
				.orElseThrow(() -> new RecordNotFoundException("Could not find user with id " + userId));
	  }

	public List<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	  // Create new user
	  public User createUser(User newUser) {
			return userRepository.save(newUser);
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
	  public void likeProduct(long userId, long productId) throws Exception {
			List<ProductUser> productUserList = productUserRepository.findProductUserByUserIdAndProductId(userId, productId);
			if (productUserList.size() == 0) {
				ProductUser productUser = new ProductUser();
				doLike(productUser, productId, userId);
			} else if (!productUserList.get(0).getUserLike()) {
				ProductUser productUser = productUserList.get(0);
				doLike(productUser, productId, userId);
			} else {
				throw	new Exception("The user with id " + userId + " already did like to this product " + productId);
			}
		}
		private void doLike(ProductUser productUser, long productId, long userId) {
			productUser.setProduct(productRepository.getOne(productId));
			productUser.setUser(userRepository.getOne(userId));
			boolean like = productUser.getUserLike();
			Integer likes = productUser.getProduct().getLikes();
			productUser.setUserLike(!like);
			if (!like) {
				productUser.getProduct().setLikes(likes + 1);
			} else {
				productUser.getProduct().setLikes(likes - 1);
			}
			productUserRepository.save(productUser);
		}

	  // Buy product by user
	  public void buyProduct(long userId, long productId) throws Exception{
			List<ProductUser> productUserList = productUserRepository.findProductUserByUserIdAndProductId(userId, productId);
			if (productUserList.size() == 0 ) {
				ProductUser productUser = new ProductUser();
				buy(productUser, productId, userId);
			} else if (!productUserList.get(0).getBought()) {
				ProductUser productUser = productUserList.get(0);
				buy(productUser, productId, userId);
			} else {
				throw	new Exception("The user with id " + userId + " already bought the product with id " + productId);
			}
		}
		private void buy(ProductUser productUser, long productId, long userId) {
			productUser.setProduct(productRepository.getOne(productId));
			productUser.setUser(userRepository.getOne(userId));

			Integer sold = productUser.getProduct().getSold();
			productUser.setBought(true);
			productUser.getProduct().setSold(sold + 1);
			productUserRepository.save(productUser);
		}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		List<User> users = userRepository.findByUsername(s);
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("Invalid Username or password");
		} else {
			return users.get(0);
		}
	}
}