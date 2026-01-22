package com.alexd.movieS.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.alexd.movieS.DTO.AuthResponse;
import com.alexd.movieS.DTO.LoginRequest;

@Service
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public AuthService (AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
	}
	
	public AuthResponse login (LoginRequest request) {
		
		Authentication auth = authenticationManager.authenticate(
			    new UsernamePasswordAuthenticationToken(
			        request.getEmail(),
			        request.getPassword()
			    )
			);

			String token = jwtService.generateToken(auth);
			
			return new AuthResponse(token);
	}
}