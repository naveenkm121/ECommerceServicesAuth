package com.ecommerce.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ui.models.Category;
import com.ecommerce.ui.models.Products;
import com.ecommerce.ui.models.Users;
import com.ecommerce.ui.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {
	

	@Autowired UserService userService;
	
	
	
	@GetMapping("/{id}")
    public String getUserInfoById(@PathVariable int id ){
		
		System.out.println("userId  "+id);
        return userService.getUserById(id);
    }
	
	@GetMapping("")
    public String getUserList( ){
        return userService.getUserList();
    }
	
	
	 @PostMapping("/signup")
	 public String createUsers(@RequestBody Users newUser) {
	    return userService.createUser(newUser);
	 }
	


	 @DeleteMapping("/{id}")
	  public String deleteUserById(@PathVariable int id ){
			
			System.out.println("delete  "+id);
	        return userService.deleteUserById(id);
	  }
		
		
	 @PatchMapping("/")
	 public String updateUser(@RequestBody Users newUser) {
		    return userService.updateUser(newUser);
	 }

		

	
}
