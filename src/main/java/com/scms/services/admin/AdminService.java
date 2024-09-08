package com.scms.services.admin;

import java.util.Optional;

import com.scms.entities.Admin;
import com.scms.entities.Student;

public interface AdminService {
	
	Optional<Admin> findByEmail(String email);
	
	public Student postStudent(Student student);
	
	

}
