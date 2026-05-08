package com.alexd.movieS.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexd.movieS.DTO.MovieDto;
import com.alexd.movieS.DTO.MovieSearchResult;
import com.alexd.movieS.service.TmdbMovieService;

@RestController
@RequestMapping ("/api/tmdb")
public class TmdbMovieController {
	
	private final TmdbMovieService movieService;
	
	public TmdbMovieController (TmdbMovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/search")
	public List<MovieSearchResult> search (String query) {
		return movieService.searchMovies(query);
	}
	
	@GetMapping("/movie/{id}")
	public MovieDto getFullMovieDetails (@PathVariable Long id) {
		return movieService.getMovieDetails(id);
	}
}
