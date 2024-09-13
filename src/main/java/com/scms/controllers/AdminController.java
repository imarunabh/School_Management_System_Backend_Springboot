package com.scms.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scms.entities.Admin;
import com.scms.entities.Student;
import com.scms.entities.User;
import com.scms.services.admin.AdminServiceImpl;
import com.scms.services.cloudinary.CloudinaryImageServiceImpl;
import com.scms.services.user.UserServiceImpl;

@RestController
@RequestMapping("/api/admin")
public class AdminController{
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	
	@GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId){
    	User user = userServiceImpl.findById(adminId);
    	if(user==null) {
    		return ResponseEntity.notFound().build();
    	}
    	 Optional<Admin> optAdmin = adminServiceImpl.findByEmail(user.getEmail());
    	 if(optAdmin.isPresent()) {
    		 return ResponseEntity.ok(optAdmin.get());
    	 }
    	 return ResponseEntity.notFound().build();
    }
	
	

}
