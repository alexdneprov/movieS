
package com.alexd.movieS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alexd.movieS.DTO.MovieRequest;
import com.alexd.movieS.Entities.MovieEntity;
import com.alexd.movieS.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	
	final
	MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping
	public List<MovieEntity> getAllMovies () {
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById (@PathVariable Long id) {
		Optional<MovieEntity> data = movieService.getMovieById(id);

        return data.map(ResponseEntity::ok)
        		.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

	@PostMapping("/add")
	public ResponseEntity<MovieEntity> addMovie (
			@RequestBody MovieRequest body) 
	{
		MovieEntity movieEntity = new MovieEntity();
	    movieEntity.setName(body.getName());
	    movieEntity.setYear(body.getYear());
		
		MovieEntity movieObj = movieService.saveOrUpdateMovie(movieEntity);
		return ResponseEntity.ok(movieObj);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<MovieEntity> updateMovie(@PathVariable Long id, @RequestBody MovieEntity newMovie) {
	    Optional<MovieEntity> data = movieService.getMovieById(id);
	    
	    if (data.isPresent()) {
	        MovieEntity updatedMovie = data.get();
	        updatedMovie.setName(newMovie.getName());
	        updatedMovie.setYear(newMovie.getYear());
	        
	        MovieEntity updData = movieService.saveOrUpdateMovie(updatedMovie);
	        return new ResponseEntity<>(updData, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteMovie (@PathVariable Long id) {
		movieService.deleteMovie(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
