package com.scms.services.teacher;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scms.entities.Student;
import com.scms.entities.Teacher;
import com.scms.entities.User;
import com.scms.enums.UserRole;
import com.scms.repository.TeacherRepository;
import com.scms.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
//	@PostConstruct
	public void createStudent() {
		User user = new User();
		user.setEmail("teacher@test.com");
		user.setPassword(new BCryptPasswordEncoder().encode("teacher"));
		user.setRole(UserRole.TEACHER);
		userRepository.save(user);
		
		Teacher teacher = new Teacher();
		teacher.setAddress("TeacherAddress");
		teacher.setBloodGroup("A+");
		teacher.setEmail("teacher@test.com");
		teacher.setName("Teacher");
		teacher.setPassword(new BCryptPasswordEncoder().encode("teacher"));
		teacher.setAge(20);
		teacher.setDob(new Date());
		teacher.setSalary(100000L);
		teacherRepository.save(teacher);
	}

	@Override
	public Teacher getTeacherById(Long Id) {
		Optional<Teacher> optTeacher = teacherRepository.findById(Id);
		if(optTeacher.isPresent()) {
			return optTeacher.get();
		}
		return null;
	}

}
