package com.alexd.movieS.DTO;

public class RegisterResponse {
	
	Long id;
	String email;
	
	public RegisterResponse (Long id, String username) {
		this.id = id;
		this.email = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	};
	
}
