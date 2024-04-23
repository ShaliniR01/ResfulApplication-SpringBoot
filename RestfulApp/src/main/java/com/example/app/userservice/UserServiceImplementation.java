package com.example.app.userservice;

import java.util.HashMap;
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
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(users==null) users = new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;
	}
}