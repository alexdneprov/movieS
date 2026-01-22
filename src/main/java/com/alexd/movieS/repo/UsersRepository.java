package com.alexd.movieS.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexd.movieS.Entities.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(String email); 
    boolean existsByUsername(String email);
}
