package com.scms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scms.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	
	Optional<Teacher> findFirstByEmail(String email);

}
