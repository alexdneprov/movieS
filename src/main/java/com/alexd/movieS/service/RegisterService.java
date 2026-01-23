package com.alexd.movieS.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alexd.movieS.DTO.RegisterRequest;
import com.alexd.movieS.DTO.RegisterResponse;
import com.alexd.movieS.Entities.UserEntity;
import com.alexd.movieS.repo.UsersRepository;

@Service
public class RegisterService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    
    public RegisterService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request) {

        UserEntity user = new UserEntity();
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        UserEntity savedUser = usersRepository.save(user);

        return new RegisterResponse (savedUser.getId(), savedUser.getUsername());
    }
}
