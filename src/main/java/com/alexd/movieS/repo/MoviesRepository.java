package com.alexd.movieS.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alexd.movieS.Entities.MovieEntity;

@Repository
public interface MoviesRepository extends JpaRepository<MovieEntity, Long>{
	
}
