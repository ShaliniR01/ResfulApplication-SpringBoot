package com.example.app.userservice;

import com.example.app.model.request.UserDetailsRequestModel;
import com.example.app.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
