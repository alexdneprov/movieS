package com.alexd.movieS.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alexd.movieS.DTO.AuthResponse;
import com.alexd.movieS.DTO.RegisterRequest;
import com.alexd.movieS.Entities.UserEntity;
import com.alexd.movieS.repo.UsersRepository;

@Service
public class RegisterService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegisterService(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {

        UserEntity user = new UserEntity();
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        usersRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(authentication);

        return new AuthResponse(token);
    }
}
