package com.alexd.movieS.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexd.movieS.DTO.AuthResponse;
import com.alexd.movieS.DTO.RegisterRequest;
import com.alexd.movieS.Entities.UserEntity;
import com.alexd.movieS.service.JwtService;
import com.alexd.movieS.service.RegisterService;

@RestController
@RequestMapping ("/auth")
public class RegisterController {
	
	private final RegisterService registerService;
	private final JwtService jwtService;
	
	public RegisterController(RegisterService registerService,
            JwtService jwtService) {
				this.registerService = registerService;
				this.jwtService = jwtService;
			}
	
	@PostMapping ("/register")
	public ResponseEntity<?> register (@RequestBody RegisterRequest request){
		try {
            AuthResponse ar = registerService.register(request);
            
            return ResponseEntity.ok(
                Map.of("token", ar)
            );

        } catch (IllegalStateException e) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("error", e.getMessage()));
        }
	}
}
