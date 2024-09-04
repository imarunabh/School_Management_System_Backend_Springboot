package com.scms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scms.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	Optional<Admin> findFirstByEmail(String email);

}
