package com.alexd.movieS.Entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "users")
public class UserEntity implements UserDetails {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false, unique = true)
	private String username;
	
	@Column (nullable = false)
	private String password;
	
	@Column (nullable = false)
	private String role;
	
	@Column(nullable = false)
    private boolean enabled = true;
	
	public UserEntity () {
		
	}
	
	public UserEntity(String email, String password, String role) {
        this.username = email;
        this.password = password;
        this.role = role;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
    public boolean isEnabled() {
        return enabled;
    }

	
	public String getPassword() {
		return password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
