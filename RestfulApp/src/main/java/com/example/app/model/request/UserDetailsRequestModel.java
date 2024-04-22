package com.example.app.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetailsRequestModel {
	@NotNull(message="first name cannot be null")
	@Size(min=2, message="first name must not be less than two characters")
	private String firstName;
	
	@NotNull(message="last name cannot be null")
	@Size(min=2, message="last name must not be less than two characters")
	private String lastName;
	
	@NotNull(message="email cannot be null")
	@Email
	private String email;
	
	@NotNull(message="password cannot be null")
	@Size(min=8, max=16, message="password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
