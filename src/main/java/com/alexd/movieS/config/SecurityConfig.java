package com.alexd.movieS.config;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JdbcUserDetailsManager users (DataSource dataSource, PasswordEncoder encoder) {
		UserDetails admin = User.builder()
				.username("admin")
				.password(encoder.encode("pass"))
				.roles("ADMIN")
				.build();

		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.createUser(admin);
		return manager;
	}
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Secure /admin/** endpoints
                .requestMatchers("/**").permitAll()    // Allow unauthenticated access to /public/** endpoints
                .anyRequest().authenticated()  // Secure other endpoints
            )
            .formLogin(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());  // Disable CSRF protection using the new method

        return http.build();
    }
	
	/*
	
	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http
		
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest()
						.authenticated()
						)
				.formLogin(Customizer.withDefaults());
				
		return http.build();
	}
	
	*/
}
