package com.alexd.movieS.service;

import java.util.Date;
import java.security.Key;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "my-super-secret-key-my-super-secret-key";
	
	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	private static final long EXPIRATION = 1000 * 60 * 60 * 24;
	
	public String generateToken(Authentication authentication) {
	    
		UserDetails user = (UserDetails) authentication.getPrincipal();

	    return Jwts.builder()
	        .setSubject(user.getUsername())
	        .claim("roles", user.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .toList())
	        .setIssuedAt(new Date())
	        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
	        .signWith(key, SignatureAlgorithm.HS256)
	        .compact();
	}
	
	public String extractUsername(String token) {
	    return Jwts.parserBuilder()
	               .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
	               .build()
	               .parseClaimsJws(token)
	               .getBody()
	               .getSubject();
	}
	
	public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
