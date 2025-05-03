package com.alexd.movieS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexd.movieS.Entities.MovieEntity;
import com.alexd.movieS.repo.MoviesRepository;

@Service
public class MovieService {
	
	@Autowired
	MoviesRepository moviesRepository;
	
	
	public List<MovieEntity> getAllMovies () {
		List<MovieEntity> moviesList = new ArrayList<>();
		
		moviesRepository.findAll().forEach(movie -> moviesList.add(movie));
		
		return moviesList;
	}
	
	public Optional<MovieEntity> getMovieById (Long id) {
		return moviesRepository.findById(id);
	}
	
	
	public MovieEntity saveOrUpdateMovie (MovieEntity movieEntity) {
		return moviesRepository.save(movieEntity);
	}
	
	public boolean deleteMovie (Long id) {
		moviesRepository.deleteById(id);
		
		if(moviesRepository.findById(id) == null) {
			return true;
		}
		
		return false;
	}
}
