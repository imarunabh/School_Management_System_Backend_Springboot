package com.scms.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scms.entities.Teacher;
import com.scms.entities.User;
import com.scms.repository.TeacherRepository;
import com.scms.services.teacher.TeacherServiceImpl;
import com.scms.services.user.UserServiceImpl;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherServiceImpl teacherServiceImpl;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/{teacherId}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable Long teacherId){
		System.out.println("This is running");
		User user = userServiceImpl.findById(teacherId);
		if(user==null){
			return ResponseEntity.notFound().build();
		}
		Optional<Teacher> optTeacher = teacherRepository.findFirstByEmail(user.getEmail());
		return ResponseEntity.ok(optTeacher.get());
		
	}

}
