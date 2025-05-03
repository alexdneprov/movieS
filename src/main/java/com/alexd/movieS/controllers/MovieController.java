
package com.alexd.movieS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alexd.movieS.Entities.MovieEntity;
import com.alexd.movieS.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping
	public List<MovieEntity> getAllMovies () {
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieEntity> getMovieById (@RequestParam Long id) {
		Optional<MovieEntity> data = movieService.getMovieById(id);
		
		if(data.isPresent()) return ResponseEntity.ok(data.get());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<MovieEntity> addMovie (
			@RequestParam String name,
		    @RequestParam int year) 
	{
		MovieEntity movieEntity = new MovieEntity();
	    movieEntity.setName(name);
	    movieEntity.setYear(year);
		
		MovieEntity movieObj = movieService.saveOrUpdateMovie(movieEntity);
		return ResponseEntity.ok(movieObj);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<MovieEntity> updateMovie(@RequestParam Long id, @RequestBody MovieEntity newMovie) {
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
