package com.ecommerce.ui.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.dto.BaseApiResModel;
import com.ecommerce.ui.dto.CartDTO;
import com.ecommerce.ui.dto.CartItem;
import com.ecommerce.ui.dto.LoginBaseApiResModel;
import com.ecommerce.ui.dto.LoginReq;
import com.ecommerce.ui.dto.UserDTO;
import com.ecommerce.ui.dto.WishlistDTO;
import com.ecommerce.ui.entity.Address;
import com.ecommerce.ui.entity.Cart;
import com.ecommerce.ui.entity.Products;
import com.ecommerce.ui.entity.Users;
import com.ecommerce.ui.entity.Wishlist;
import com.ecommerce.ui.repository.AddressRepository;
import com.ecommerce.ui.repository.CartRepository;
import com.ecommerce.ui.repository.UserRepository;
import com.ecommerce.ui.repository.WishlistRepository;
import com.ecommerce.ui.utils.BaseApiResponse;
import com.ecommerce.ui.utils.CommonUtility;
import com.ecommerce.ui.utils.GsonHelper;

@Service
public class UserService {

	@Autowired
	GsonHelper gsonHelper;
	@Autowired
	UserRepository userRepository;

	@Autowired
	WishlistRepository wishlistRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	AddressRepository addressRepository;

	public String getUserList() {

		BaseApiResModel apiResponse = new BaseApiResModel();
		List<Users> userList = userRepository.findAll();

		if (userList != null & userList.size() > 0) {
			apiResponse.setData(userList);
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);

		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}

		return gsonHelper.toJson(apiResponse);
	}

	public Users getUserDetailByEmail(String email) {
		Users userDetail = userRepository.findByEmail(email).orElse(null);
		return userDetail;

	}

	public BaseApiResponse checkLogin(LoginReq loginReq) {
		BaseApiResModel apiResponse = new LoginBaseApiResModel();
		Users userDetail = userRepository.findByEmail(loginReq.username).orElse(null);
		// Optional<Users> userDetail=
		// userRepository.findByEmail(loginReq.username);

		if (userDetail != null) {
			UserDTO userDTO = new UserDTO();
			// Users userDetail = users.get();

			if (userDetail.getPassword().equals(loginReq.getPassword())) {

				userDTO.setId(userDetail.getId());
				userDTO.setName(userDetail.getName());
				userDTO.setEmail(userDetail.getEmail());
				userDTO.setMobile(userDetail.getMobile());
				userDTO.setDob(userDetail.getDob());
				userDTO.setCreatedDate(userDetail.getCreatedDate());
				userDTO.setUpdatedDate(userDetail.getUpdatedDate());
				userDTO.setRole(userDetail.getRole());
				userDTO.setImage(userDetail.getImage());

				apiResponse.setData(userDTO);
				apiResponse.setStatus(1);
				apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			} else {

				apiResponse.setData(null);
				apiResponse.setStatus(0);
				apiResponse.setMessage("Failure: Incorrect username or password !!!");

			}

		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("User with given Email id doesn't exits ");
		}
		return apiResponse;

	}

	public String getUserById(Long id) {

		BaseApiResModel apiResponse = new BaseApiResModel();
		Optional<Users> userDetails = userRepository.findById(id);

		if (userDetails.isPresent()) {
			apiResponse.setData(userDetails.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);

		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}

		return gsonHelper.toJson(apiResponse);
	}

	public String createUser(Users user) {

		BaseApiResModel apiResponse = new BaseApiResModel();

		if (user != null && !user.getName().equals("")) {

			if (CommonUtility.isValidEmail(user.getEmail()) && CommonUtility.isValidMobileNumber(user.getMobile())) {

				Boolean isUserExits = isUserExists(user.getEmail(), user.getMobile());

				if (isUserExits) {
					apiResponse.setMessage("User with same Email/Mobile already exits ");
					apiResponse.setStatus(0);
				} else {
					user.setCreatedDate(new Date());
					user.setUpdatedDate(new Date());
					Users createdUser = userRepository.save(user);
					apiResponse.setData(createdUser);
					apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
					apiResponse.setMessage(AppConstants.SUCCESS_MSG);
				}
			} else if (!CommonUtility.isValidEmail(user.getEmail())) {
				apiResponse.setMessage("Email is not valid ");
				apiResponse.setStatus(AppConstants.FAILURE_STATUS);
			} else if (CommonUtility.isValidMobileNumber(user.getMobile())) {
				apiResponse.setMessage("Mobile  is not valid ");
				apiResponse.setStatus(AppConstants.FAILURE_STATUS);
			}

		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("Error : Username cannot be null");
		}

		return gsonHelper.toJson(apiResponse);
	}

	public String deleteUserById(Long id) {
		BaseApiResModel apiResponse = new BaseApiResModel();

		Optional<Users> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
			apiResponse.setData(user.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage("User with id =" + id + " delete successfully");
		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("User with id =" + id + " Not Found");
		}

		return gsonHelper.toJson(apiResponse);
	}

	public String updateUser(long id, Users newUser) {
		boolean isValidData = true;
		String errorMessage = AppConstants.FAILURE_MSG;
		BaseApiResModel apiResponse = new BaseApiResModel();

		Optional<Users> existingUserOptional = userRepository.findById(id);
		if (existingUserOptional.isPresent()) {
			Users existingUser = existingUserOptional.get();

			if (newUser.getName() != null && !existingUser.getName().isEmpty())
				existingUser.setName(newUser.getName());
			if (newUser.getDob() != null)
				existingUser.setDob(newUser.getDob());
			if (newUser.getEmail() != null) {
				boolean isValidEmail = CommonUtility.isValidEmail(newUser.getEmail());
				if (isValidEmail) {
					existingUser.setEmail(newUser.getEmail());
				} else {
					isValidData = false;
					errorMessage = "Email is not  valid";
				}
			}

			if (newUser.getMobile() != null) {
				boolean isValidMobile = CommonUtility.isValidMobileNumber(newUser.getMobile());
				if (isValidMobile) {
					existingUser.setMobile(newUser.getMobile());
				} else {
					isValidData = false;
					errorMessage = "Mobile is not valid";
				}
			}
			if (isValidData) {
				existingUser.setUpdatedDate(new Date());
				existingUser = userRepository.saveAndFlush(existingUser);
				apiResponse.setData(existingUser);
				apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
				apiResponse.setMessage("User Updated the successfully");
			} else {
				apiResponse.setStatus(AppConstants.FAILURE_STATUS);
				apiResponse.setMessage(errorMessage);
			}
			;
		} else {
			apiResponse.setStatus(AppConstants.FAILURE_STATUS);
			apiResponse.setMessage("User  does't not exits");
		}

		return gsonHelper.toJson(apiResponse);
	}

	public boolean isUserExists(String email, String mobile) {
		Optional<Users> userDetails = userRepository.findByEmail(email);
		boolean emailExists = userDetails.isPresent();
		userDetails = userRepository.findByMobile(mobile);
		boolean mobieExits = userDetails.isPresent();
		return emailExists || mobieExits;
	}

	public BaseApiResModel addToWishlist(Wishlist wishlistItem, long userId) {
		BaseApiResModel apiResponse = new BaseApiResModel();
		Optional<Wishlist> exitingWishListItem = wishlistRepository.findByProdIdAndUserId(wishlistItem.getProdId(),
				userId);
		if (exitingWishListItem.isPresent()) {
			wishlistRepository.deleteById(exitingWishListItem.get().getId());
			apiResponse.setData(exitingWishListItem.get());
			apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
			apiResponse.setMessage("Item has been removed from wishlist successfully");
		} else {
			wishlistItem.setUserId(userId);
			wishlistRepository.save(wishlistItem);
			apiResponse.setData(wishlistItem);
			apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
			apiResponse.setMessage("Item has been successfully added to wishlist");

		}
		return apiResponse;
	}

	public BaseApiResModel getWishlistByUserId(long userId) {
		BaseApiResModel apiResponse = new BaseApiResModel();
		List<WishlistDTO> wishlistItems = wishlistRepository.getWishlistByUserId(userId);
		apiResponse.setData(wishlistItems);
		apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
		apiResponse.setMessage(AppConstants.SUCCESS_MSG);
		return apiResponse;
	}

	public BaseApiResModel removeFromWishlist(long id) {
		BaseApiResModel apiResponse = new BaseApiResModel();

		Optional<Wishlist> wishlistDetail = wishlistRepository.findById(id);
		System.out.println(
				"WishList Item ==" + wishlistDetail.get().getId() + "  pid==" + wishlistDetail.get().getProdId());
		if (wishlistDetail.isPresent()) {

			wishlistRepository.deleteById(id);
			apiResponse.setData(wishlistDetail.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage("Wishlist with id =" + id + " delete successfully");
		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("Wishlist with id =" + id + " Not Found");
		}
		return apiResponse;
	}

	public BaseApiResModel addUserAddress(Address address) {
		BaseApiResModel apiResponse = new BaseApiResModel();
		if (address.getAddress1() != null && !address.getAddress1().equals("")) {
			boolean isValidMobile = CommonUtility.isValidMobileNumber(address.getMobile());
			if (isValidMobile) {
				addressRepository.save(address);
				apiResponse.setData(address);
				apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
				apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			} else {
				apiResponse.setMessage("Mobile Number is invalid");
			}
		} else {
			apiResponse.setMessage("Invalid Street  name !!!");
		}
		return apiResponse;
	}

	public BaseApiResModel getAddressByUserId(long userId) {
		BaseApiResModel apiResponse = new BaseApiResModel();
		List<Address> addresses = addressRepository.findByUserId(userId);
		apiResponse.setData(addresses);
		apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
		apiResponse.setMessage(AppConstants.SUCCESS_MSG);
		return apiResponse;
	}

	public BaseApiResModel deleteAddressById(int id) {
		BaseApiResModel apiResponse = new BaseApiResModel();

		Optional<Address> addressDetail = addressRepository.findById(id);
		if (addressDetail.isPresent()) {
			addressRepository.deleteById(id);
			apiResponse.setData(addressDetail.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage("Address with id =" + id + " delete successfully");
		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("Address with id =" + id + " Not Found");
		}
		return apiResponse;
	}

	public BaseApiResModel updateAddressById(Address newAddress, int id) {

		BaseApiResModel apiResponse = new BaseApiResModel();

		Optional<Address> existingOptionalAddress = addressRepository.findById(id);
		if (existingOptionalAddress.isPresent()) {
			Address existingAddress = existingOptionalAddress.get();
			if (newAddress.getName() != null && !newAddress.getName().isEmpty())
				existingAddress.setName(newAddress.getName());
			if (newAddress.getMobile() != null && !newAddress.getMobile().isEmpty())
				existingAddress.setMobile(newAddress.getMobile());
			if (newAddress.getAddress1() != null && !newAddress.getAddress1().isEmpty())
				existingAddress.setAddress1(newAddress.getAddress1());
			if (newAddress.getAddress2() != null && !newAddress.getAddress2().isEmpty())
				existingAddress.setAddress2(newAddress.getAddress2());
			if (newAddress.getLandmark() != null && !newAddress.getLandmark().isEmpty())
				existingAddress.setLandmark(newAddress.getLandmark());
			if (newAddress.getCity() != null && !newAddress.getCity().isEmpty())
				existingAddress.setCity(newAddress.getCity());
			if (newAddress.getPincode() != null && !newAddress.getPincode().isEmpty())
				existingAddress.setPincode(newAddress.getPincode());
			if (newAddress.getState() != null && !newAddress.getState().isEmpty())
				existingAddress.setState(newAddress.getState());
			if (newAddress.getCountry() != null && !newAddress.getCountry().isEmpty())
				existingAddress.setCountry(newAddress.getCountry());
			if (newAddress.getIsDefault() == 1)
				existingAddress.setIsDefault(newAddress.getIsDefault());

			newAddress = addressRepository.saveAndFlush(existingAddress);
			apiResponse.setData(newAddress);
			apiResponse.setStatus(1);
			apiResponse.setMessage("Address Updated the successfully");
		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("Address  does't not exits");
		}

		return apiResponse;
	}

	public BaseApiResModel getCartItemByUserId(long userId) {
		BaseApiResModel apiResponse = new BaseApiResModel();

		List<CartItem> cartItems = cartRepository.getCartItemByUserId(userId);
		if (!cartItems.isEmpty()) {
			CartDTO cartDTO = new CartDTO();
			cartDTO.setUserId(userId);
			cartDTO.setTotalProducts(cartItems.size());
			cartDTO.setCartItems(cartItems);

			for (CartItem cartItem : cartItems) {
				cartDTO.setTotalDiscountPrice(cartItem.getTotalDiscountPrice() + cartDTO.getTotalDiscountPrice());
				cartDTO.setTotalPrice(cartItem.getTotalPrice() + cartDTO.getTotalPrice());
				cartDTO.setTotalQuantity(cartItem.getQuantity() + cartDTO.getTotalQuantity());
			}

			apiResponse.setData(cartDTO);
			apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
		} else {
			apiResponse.setStatus(AppConstants.FAILURE_STATUS);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}

		return apiResponse;
	}

	public BaseApiResModel addToCart(Cart cartItem, long userId) {
		BaseApiResModel apiResponse = new BaseApiResModel();
		cartItem.setUserId(userId);
		Optional<Cart> exitingOptionalCartItem = cartRepository.findByProdIdAndUserId(cartItem.getProdId(),cartItem.getUserId());
		if (exitingOptionalCartItem.isPresent()) {
			 Cart existingCart = exitingOptionalCartItem.get();
			
			existingCart.setQuantity(cartItem.getQuantity());
			existingCart.setCreatedDate(new Date());
			Cart saveAndFlush = cartRepository.saveAndFlush(existingCart);
			
			BaseApiResponse presentCartItemRes= getCartItemByUserId(existingCart.getUserId());
			apiResponse.setData(presentCartItemRes.getData());
			apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
			apiResponse.setMessage("Cart Updated the successfully");
			
			
			apiResponse.setMessage("Product is already present in Cart");
		} else {
			cartItem.setCreatedDate(new Date());
			cartRepository.save(cartItem);
			apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
			apiResponse.setMessage("Item has been successfully added to Cart");

		}
		
		BaseApiResponse presentCartItemRes= getCartItemByUserId(userId);
		apiResponse.setData(presentCartItemRes.getData());
		apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
		return apiResponse;
	}

	public BaseApiResModel deleteCartById(long id) {
		BaseApiResModel apiResponse = new BaseApiResModel();

		Optional<Cart> cartOptional = cartRepository.findById(id);
		if (cartOptional.isPresent()) {
			cartRepository.deleteById(id);
			BaseApiResponse presentCartItemRes= getCartItemByUserId(cartOptional.get().getUserId());
			apiResponse.setData(presentCartItemRes.getData());
			apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
			apiResponse.setMessage("Product with id =" + cartOptional.get().getProdId() + " delete successfully");
		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(AppConstants.FAILURE_STATUS);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}
		return apiResponse;
	}

	public BaseApiResModel updateCartById(Cart newCart, long id) {

		BaseApiResModel apiResponse = new BaseApiResModel();

		Optional<Cart> existingOptionalCart = cartRepository.findById(id);
		if (existingOptionalCart.isPresent()) {
			Cart existingCart = existingOptionalCart.get();

			if (newCart.getQuantity() > 0) {
				existingCart.setQuantity(newCart.getQuantity());
				existingCart.setCreatedDate(new Date());
				newCart = cartRepository.saveAndFlush(existingCart);
				
				BaseApiResponse presentCartItemRes= getCartItemByUserId(existingCart.getUserId());
				apiResponse.setData(presentCartItemRes.getData());
				apiResponse.setStatus(AppConstants.SUCCESS_STATUS);
				apiResponse.setMessage("Cart Updated the successfully");
			} else {
				apiResponse.setStatus(AppConstants.FAILURE_STATUS);
				apiResponse.setMessage("Quantity should be greater than 0");
			}

		} else {
			apiResponse.setData(null);
			apiResponse.setStatus(AppConstants.FAILURE_STATUS);
			apiResponse.setMessage("Cart  does't not exits");
		}

		return apiResponse;
	}

	public Users getUserDetailFromToken() {
		UserDTO userDTO = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users userDetail = (Users) authentication.getPrincipal();
		return userDetail;
	}

}
