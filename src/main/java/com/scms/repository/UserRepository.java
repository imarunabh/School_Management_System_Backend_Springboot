package com.scms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scms.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findFirstByEmail(String email);

}
