package com.alexd.movieS.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexd.movieS.DTO.AuthResponse;
import com.alexd.movieS.DTO.LoginRequest;
import com.alexd.movieS.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping ("/auth")
public class LoginController {
	
	private final AuthService authService;
	
	public LoginController (AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping ("/login")
	public AuthResponse login (@Valid @RequestBody LoginRequest request) {
		return authService.login(request);
	}
	
	@GetMapping ("/me")
	public String me (Authentication auth) {
		return auth.getName();
	}
}
