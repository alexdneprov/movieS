package com.alexd.movieS.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alexd.movieS.Entities.MovieEntity;

@Repository
public interface MoviesRepository extends JpaRepository<MovieEntity, Long>{
	Optional<MovieEntity> findById(Long id);
	void save
}
