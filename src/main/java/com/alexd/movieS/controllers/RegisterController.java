package com.alexd.movieS.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexd.movieS.DTO.RegisterRequest;
import com.alexd.movieS.DTO.RegisterResponse;
import com.alexd.movieS.service.JwtService;
import com.alexd.movieS.service.RegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/auth")
public class RegisterController {
	
	private final RegisterService registerService;
	
	
	public RegisterController(RegisterService registerService,
            JwtService jwtService) {
				this.registerService = registerService;
			}
	
	@PostMapping ("/register")
	public ResponseEntity<RegisterResponse> register (@Valid @RequestBody RegisterRequest request){
		
		RegisterResponse response = registerService.register(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
