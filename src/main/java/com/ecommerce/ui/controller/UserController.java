package com.ecommerce.ui.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.dto.BaseApiResModel;
import com.ecommerce.ui.dto.UserDTO;
import com.ecommerce.ui.entity.Address;
import com.ecommerce.ui.entity.Cart;
import com.ecommerce.ui.entity.Category;
import com.ecommerce.ui.entity.Products;
import com.ecommerce.ui.entity.Users;
import com.ecommerce.ui.entity.Wishlist;
import com.ecommerce.ui.security.JwtTokenUtil;
import com.ecommerce.ui.service.UserService;
import com.ecommerce.ui.utils.BaseApiResponse;

@RestController
@RequestMapping("/v1")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value={"", "/","/home", "/welcome"})
	public String welcome() {
		return "Welcome to the ECommerce Rest API's";
	}
	

	@GetMapping("/users")
	public String getUserList() {
		return userService.getUserList();
	}

	@GetMapping("/users/{id}")
	public String getUserInfoById(@PathVariable Long id) {

		System.out.println("userId  " + id);
		return userService.getUserById(id);
	}

	@DeleteMapping("users/{id}")
	public String deleteUserById(@PathVariable Long id) {

		System.out.println("delete  " + id);
		return userService.deleteUserById(id);
	}

	@PutMapping("users/{id}")
	public String updateUser(@PathVariable Long id, @RequestBody Users newUser) {
		return userService.updateUser(id, newUser);
	}
	
	
	
	@PostMapping("/wishlist")
	public  ResponseEntity<BaseApiResModel> addToWishlist(@RequestBody Wishlist wishlistItem) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			baseApiResponse= userService.addToWishlist(wishlistItem,users.getId());
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/wishlist")
	public ResponseEntity<BaseApiResModel> getWishlistByUserId() {
		BaseApiResModel baseApiResponse=null;
		Users users=userService.getUserDetailFromToken();
		if(users!=null){
			baseApiResponse= userService.getWishlistByUserId(users.getId());
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/wishlist/{id}")
	public  ResponseEntity<BaseApiResModel> removeFromWishlist(@PathVariable int id) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			System.out.println("WishList Item =="+id);
			baseApiResponse= userService.removeFromWishlist(id);
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	
	@PostMapping("/address")
	public  ResponseEntity<BaseApiResModel> addUserAddress(@RequestBody Address address) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			address.setUserId(users.getId());
			if(address.getName()==null) address.setName(users.getName());
			if(address.getMobile()==null) address.setMobile(users.getMobile());
			baseApiResponse= userService.addUserAddress(address);
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/address")
	public  ResponseEntity<BaseApiResModel> getUserAddress() {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			baseApiResponse= userService.getAddressByUserId(users.getId());
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{id}")
	public  ResponseEntity<BaseApiResModel> deleteAddressById(@PathVariable int id) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			baseApiResponse= userService.deleteAddressById(id);
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@PutMapping("/address/{id}")
	public  ResponseEntity<BaseApiResModel> updateAddressById(@RequestBody Address address,@PathVariable int id) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			baseApiResponse= userService.updateAddressById(address,id);
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}

	@GetMapping("/cart")
	public ResponseEntity<BaseApiResModel> getCartByUserId() {
		BaseApiResModel baseApiResponse=null;
		Users users=userService.getUserDetailFromToken();
		if(users!=null){
			baseApiResponse= userService.getCartItemByUserId(users.getId());
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@PostMapping("/cart")
	public  ResponseEntity<BaseApiResModel> addToCart(@RequestBody Cart cartItem) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			baseApiResponse= userService.addToCart(cartItem, users.getId());
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/cart/{id}")
	public  ResponseEntity<BaseApiResModel> deleteCartById(@PathVariable int id) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			baseApiResponse= userService.deleteCartById(id);
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@PutMapping("/cart/{id}")
	public  ResponseEntity<BaseApiResModel> updateCartById(@RequestBody Cart newCart,@PathVariable int id) {
		Users users=userService.getUserDetailFromToken();
		BaseApiResModel baseApiResponse=null;
		if(users!=null){
			newCart.setUserId(users.getId());
			baseApiResponse= userService.updateCartById(newCart, id);
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/pincode/{pincode}")
	public ResponseEntity<BaseApiResModel> getPincodeDetail(@PathVariable String pincode) {
		BaseApiResModel baseApiResponse=null;
		Users users=userService.getUserDetailFromToken();
		if(users!=null){
			baseApiResponse= userService.getPincodeDetail(pincode);
		}else{
			baseApiResponse.setMessage("Unauthorise User");
		} 
		return new ResponseEntity<BaseApiResModel>(baseApiResponse, HttpStatus.OK);
	}
	
	
}	
