package com.ecommerce.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ui.dto.BaseApiResModel;
import com.ecommerce.ui.dto.LoginBaseApiResModel;
import com.ecommerce.ui.dto.LoginReq;
import com.ecommerce.ui.entity.Users;
import com.ecommerce.ui.fcm.FcmNotificationService;
import com.ecommerce.ui.fcm.PushNotificationRequest;
import com.ecommerce.ui.security.JwtTokenUtil;
import com.ecommerce.ui.service.UserService;
import com.ecommerce.ui.utils.GsonHelper;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
	
	@Autowired UserService userService;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired GsonHelper gsonHelper;
	@Autowired FcmNotificationService fcmNotificationService;
	
	
	@RequestMapping(value={"", "/","/home", "/welcome"})
	public String welcome() {
		return "Welcome to the ECommerce Rest API's";
	}
	
	 @PostMapping("/login")
	 public String login (@RequestBody LoginReq loginReq) {
		 
		 LoginBaseApiResModel apiResponse= (LoginBaseApiResModel) userService.checkLogin(loginReq);
		 if(apiResponse.getStatus()==1){
			 final String token = jwtTokenUtil.generateToken(loginReq.username);
			 apiResponse.setToken(token);
		 }
		 return gsonHelper.toJson(apiResponse);
	 }
	 
	 @PostMapping("/signup")
	 public String createUsers(@RequestBody Users newUser) {
	    return userService.createUser(newUser);
	 }
	 
	 @PostMapping("/fcm")
	 public String sendFCMNotification (@RequestBody PushNotificationRequest pushNotificationRequest) {
		 
		 return fcmNotificationService.sendNotification(pushNotificationRequest);
	 }
}
