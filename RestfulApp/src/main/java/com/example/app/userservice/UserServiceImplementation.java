package com.example.app.userservice;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.request.UserDetailsRequestModel;
import com.example.app.model.response.UserRest;
import com.example.app.shared.Utils;

@Service
public class UserServiceImplementation implements UserService {
	
	Map<String, UserRest> users;
	Utils utils;
	
	public UserServiceImplementation() {}
	
	@Autowired
	public UserServiceImplementation(Utils utils) 
	{
		this.utils=utils;
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest userDetail = new UserRest();
		userDetail.setEmail(userDetails.getEmail());
		userDetail.setFirstName(userDetails.getFirstName());
		userDetail.setLastName(userDetails.getLastName());
		
		String userId = UUID.randomUUID().toString();
		userDetail.setUserId(userId);
		
		return userDetail;
	}
}
