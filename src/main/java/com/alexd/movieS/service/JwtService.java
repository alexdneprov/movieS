package com.alexd.movieS.service;

import java.util.Date;
import java.security.Key;
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
	
	public String generateToken (String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith (key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUsername(String token) {
        return getClaims(token).getSubject();
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
