package com.example.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.exceptions.UserServiceException;
import com.example.app.model.request.UpdateUserDetailsRequestModel;
import com.example.app.model.request.UserDetailsRequestModel;
import com.example.app.model.response.UserRest;
import com.example.app.userservice.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	Map<String, UserRest> users;
	
	@Autowired
	UserService userService;

	@GetMapping
	public String getUser(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue="desc", required=false) String sort){
	
		return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}

	@GetMapping(path="/{userId}",
			produces = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId){
		/*if(true) {
			throw new UserServiceException("A user service exception is thrown");
		}*/
		
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping(consumes =  { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){
		
		UserRest returnUserDetail = userService.createUser(userDetails);
		
		if(users==null) users = new HashMap<>();
		users.put(returnUserDetail.getUserId(), returnUserDetail);

		return new ResponseEntity<UserRest>(returnUserDetail, HttpStatus.OK);
	}

	@PutMapping(path="/{userId}",
			consumes =  { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails){
		
		UserRest updatedUserDetail = users.get(userId);
		updatedUserDetail.setFirstName(userDetails.getFirstName());
		updatedUserDetail.setLastName(userDetails.getLastName());
		
		users.put(userId, updatedUserDetail);
		
		return updatedUserDetail;
	}
	
	@DeleteMapping(path="/{userid}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userid){
		
		users.remove(userid);
		
		return ResponseEntity.noContent().build();
	}


}
