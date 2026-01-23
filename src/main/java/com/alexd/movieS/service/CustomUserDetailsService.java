package com.alexd.movieS.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexd.movieS.Entities.UserEntity;
import com.alexd.movieS.repo.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UsersRepository usersRepository;
	
	public CustomUserDetailsService (UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = usersRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		return User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getRole())
				.build();
	}
}
