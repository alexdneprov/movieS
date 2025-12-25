package com.alexd.movieS.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

	@NotBlank (message = "email field cannot be left blank")
    @Email (message = "email is incorrect")
	public String email;

	@NotBlank (message = "password field cannot be left blank")
	@Size (min = 6, message = "minimum 6 symbols")
	public String password;

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
