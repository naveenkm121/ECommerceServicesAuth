package com.ecommerce.ui.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ui.constant.AppConstants;
import com.ecommerce.ui.dto.BaseApiResModel;
import com.ecommerce.ui.dto.LoginBaseApiResModel;
import com.ecommerce.ui.dto.LoginReq;
import com.ecommerce.ui.dto.UserDTO;
import com.ecommerce.ui.models.Products;
import com.ecommerce.ui.models.Users;
import com.ecommerce.ui.repository.UserRepository;
import com.ecommerce.ui.utils.BaseApiResponse;
import com.ecommerce.ui.utils.GsonHelper;


@Service
public class UserService {
	
	@Autowired GsonHelper gsonHelper;
	@Autowired  UserRepository userRepository;
	
	
	
	public String  getUserList(){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
		List<Users> userList= userRepository.findAll();
		
		if(userList!=null & userList.size()>0)
		{
			apiResponse.setData(userList);
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}
	
	public Users getUserDetailByEmail(String email){
		Users userDetail = userRepository.findByEmail(email).orElse(null);
		return userDetail;
		
	}
	
	public BaseApiResponse checkLogin(LoginReq loginReq){
		BaseApiResModel apiResponse =  new LoginBaseApiResModel();
		Users userDetail = userRepository.findByEmail(loginReq.username).orElse(null);
		//Optional<Users> userDetail= userRepository.findByEmail(loginReq.username);
		
		if(userDetail!=null){
				UserDTO userDTO = new UserDTO();
				//Users userDetail = users.get();
				
				if(userDetail.getPassword().equals(loginReq.getPassword())){
					
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
				}else{
					
					apiResponse.setData(null);
					apiResponse.setStatus(0);
					apiResponse.setMessage("Failure: Incorrect username or password !!!");
					
				}
			
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("User with given Email id doesn't exits ");
		}
		return apiResponse;
		
	} 
	
	public String  getUserById(int id){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
		Optional<Users> userDetails= userRepository.findById(id);
		
		if(userDetails.isPresent())
		{
			apiResponse.setData(userDetails.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.EMPTY_DATA_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}
	
	
	public String  createUser(Users user){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
	
		if(user!=null && !user.getName().equals(""))
		{
			user.setCreatedDate(new Date());
			user.setUpdatedDate(new Date());
			Users createdUser = userRepository.save(user);
			apiResponse.setData(createdUser);
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
			
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.FAILURE_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}
	
	
	public String deleteUserById(int id) {
		BaseApiResModel apiResponse =  new BaseApiResModel();
	
		Optional<Users> user  = userRepository.findById(id);
		if(user.isPresent())
		{ 
			userRepository.deleteById(id);
			apiResponse.setData(user.get());
			apiResponse.setStatus(1);
			apiResponse.setMessage("User with id ="+id+ " delete successfully");
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage("User with id ="+id+ " Not Found");
		}
			       
		
		return gsonHelper.toJson(apiResponse);
	} 
	
	public String  updateUser(Users newUser){
		
		BaseApiResModel apiResponse =  new BaseApiResModel();
	
		Optional<Users> user  = userRepository.findById(newUser.getId());
		if(user.isPresent())
		{ 
			userRepository.save(newUser);
			apiResponse.setData(newUser);
			apiResponse.setStatus(1);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
		}else{
			apiResponse.setData(null);
			apiResponse.setStatus(0);
			apiResponse.setMessage(AppConstants.SUCCESS_MSG);
		}
		
		return  gsonHelper.toJson(apiResponse);
	}
	
	
	

		

	

	

}
