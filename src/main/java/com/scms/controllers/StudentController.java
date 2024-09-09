package com.scms.controllers;

import java.util.List;
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

import com.scms.entities.Student;
import com.scms.entities.User;
import com.scms.repository.StudentRepository;
import com.scms.services.admin.AdminServiceImpl;
import com.scms.services.student.StudentServiceImpl;
import com.scms.services.user.UserServiceImpl;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
		System.out.println("This is running");
		User user = userServiceImpl.findById(studentId);
		if(user==null) {
			return  ResponseEntity.notFound().build();
		}
		
		Optional<Student> student = studentRepository.findFirstByEmail(user.getEmail());
		System.out.println(student.get());
		return ResponseEntity.ok(student.get());
	}
	
	@PostMapping("/create-student")
	public ResponseEntity<?> saveStudent(@RequestBody Student student){
		Student createdStudent = adminServiceImpl.postStudent(student);
		if(createdStudent==null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);	
	}
	
	@GetMapping("/get-All-Student")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> allStudents = studentServiceImpl.getAllStudents();
		return ResponseEntity.ok(allStudents);
		
		
	}

}
