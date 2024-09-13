package com.scms.services.admin;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scms.entities.Admin;
import com.scms.entities.Student;
import com.scms.entities.User;
import com.scms.enums.UserRole;
import com.scms.repository.AdminRepository;
import com.scms.repository.StudentRepository;
import com.scms.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
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

	@Override
	public Optional<Admin> findByEmail(String email) {
	    Optional<Admin> optAdmin = adminRepository.findFirstByEmail(email);
	    if(optAdmin.isPresent()) {
	    	return optAdmin;
	    }
	    return Optional.empty();
	}

	@Override
	public Student postStudent(Student student) {
		 Optional<Student> optStudent = studentRepository.findFirstByEmail(student.getEmail());
		 if(optStudent.isPresent()) {
			 return null;
		 }
		Student newstudent = new Student();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		newstudent.setAddress(student.getAddress());
		newstudent.setBloodGroup(student.getBloodGroup());
		newstudent.setDob(student.getDob());
		newstudent.setEmail(student.getEmail());
		newstudent.setFatherName(student.getFatherName());
		newstudent.setMotherName(student.getMotherName());
		newstudent.setName(student.getName());
		newstudent.setRole(UserRole.STUDENT);
		newstudent.setS_Class(student.getS_Class());
		newstudent.setPassword(new BCryptPasswordEncoder().encode(student.getPassword()));
		newstudent.setAge(calculateAge(simpleDateFormat.format(student.getDob())));
		newstudent.setImage(student.getImage());
		studentRepository.save(newstudent);
		
		User user = new User();
		user.setEmail(student.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(student.getPassword()));
		user.setRole(UserRole.STUDENT);
		userRepository.save(user);
		System.out.println(newstudent);
		return newstudent;
	
	}
	
	public int calculateAge(String dob) {
	    // Parse the input date (assuming it's in "yyyy-MM-dd" format)
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate birthDate = LocalDate.parse(dob, formatter);

	    // Get the current date
	    LocalDate currentDate = LocalDate.now();

	    // Calculate the period between the birth date and current date
	    return Period.between(birthDate, currentDate).getYears();
	}

	
	
}
