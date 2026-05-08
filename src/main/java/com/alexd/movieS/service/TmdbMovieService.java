package com.alexd.movieS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.alexd.movieS.DTO.MovieDto;
import com.alexd.movieS.DTO.MovieSearchResult;
import com.alexd.movieS.DTO.TmdbMovieItem;
import com.alexd.movieS.DTO.TmdbSearchResponse;



@Service
public class TmdbMovieService {
	
	private String apiKey; 
	private final WebClient webClient;
	private final String IMG_BASE = "https://image.tmdb.org/t/p/";
	
	public TmdbMovieService (WebClient webClient, @Value("${tmdb.api.key}") String apiKey) {
		this.webClient = webClient;
		this.apiKey = apiKey;
	}
	
	public List<MovieSearchResult> searchMovies(String query) {
	    return webClient.get()
	        .uri(uriBuilder -> uriBuilder
	            .path("/search/movie")
	            .queryParam("api_key", apiKey)
	            .queryParam("query", query)
	            .build())
	        .retrieve()
	        .bodyToMono(TmdbSearchResponse.class)
	        .map((TmdbSearchResponse response) -> {
	            return response.results.stream()
	                .map(item -> new MovieSearchResult(
	                    item.id,
	                    item.title,
	                    item.releaseDate,
	                    item.posterPath != null ? IMG_BASE + "w200" + item.posterPath : null,
	                    item.voteAverage
	                ))
	                .collect(Collectors.toList());
	        	})
	        .block();
	}
	
	public MovieDto getMovieDetails(Long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}")
                        .queryParam("api_key", apiKey)
                        .build(id))
                .retrieve()
                .bodyToMono(TmdbMovieItem.class)
                .map(item -> new MovieDto(
                        item.id,
                        item.title,
                        item.overview,
                        item.releaseDate,
                        item.posterPath != null ? IMG_BASE + "w500" + item.posterPath : null,
                        item.voteAverage
                ))
                .block();
    }
}
