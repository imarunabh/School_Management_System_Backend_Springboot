package com.scms.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scms.entities.Admin;
import com.scms.entities.User;
import com.scms.enums.UserRole;
import com.scms.repository.AdminRepository;
import com.scms.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
//	@PostConstruct
	public void createAdmin() {
		User user= new User();
		user.setEmail("admin@test.com");
		user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		user.setRole(UserRole.ADMIN);
		userRepository.save(user);
		
		Admin admin = new Admin();
		admin.setAddress("AdminAddress");
		admin.setBloodGroup("A+");
		admin.setEmail("admin@test.com");
		admin.setName("Admin");
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
		admin.setPost_School("Admin");
		adminRepository.save(admin);
	}

}
