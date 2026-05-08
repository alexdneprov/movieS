package com.alexd.movieS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient tmdbWebClient(WebClient.Builder builder) {
		return builder
				.baseUrl("https://api.tmdb.org/3")
				.defaultHeader("Accept","application/json")
				.build();
	}
}
