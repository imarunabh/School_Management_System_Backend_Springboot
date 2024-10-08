package com.scms.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scms.entities.Attendance;
import com.scms.entities.Student;
import com.scms.entities.User;
import com.scms.enums.AttendaceStatus;
import com.scms.repository.StudentRepository;
import com.scms.services.admin.AdminServiceImpl;
import com.scms.services.attendance.AttendanceServiceImpl;
import com.scms.services.cloudinary.CloudinaryImageServiceImpl;
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
	
	@Autowired
	private CloudinaryImageServiceImpl cloudinaryImageServiceImpl;
	
	@Autowired
	private AttendanceServiceImpl attendanceServiceImpl;
	
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
	public ResponseEntity<?> saveStudent(@RequestPart("student")Student student ,@RequestPart("image") MultipartFile file){
        System.out.println(student);
        System.out.println(file);
		Map data = this.cloudinaryImageServiceImpl.upload(file);
		if(data.containsKey("url")) {
			student.setImage((String)data.get("url"));
		}
		Student createdStudent = adminServiceImpl.postStudent(student);
		if(createdStudent==null) {
			return new ResponseEntity<>("Something went wrong " + data,HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);	
//		return null;
		
	}
	
	
	@GetMapping("/get-All-Student")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> allStudents = studentServiceImpl.getAllStudents();
		return ResponseEntity.ok(allStudents);

	}
	
	@PostMapping("/get-attendance-email")
	public ResponseEntity<List<Attendance>> findAttendanceByEmail(@RequestBody String email){
		System.out.println(email);
		List<Attendance> attendance = attendanceServiceImpl.getAttendance(email);
		System.out.println(attendance);
		return ResponseEntity.ok(attendance);
		
	}
	
	@PostMapping("/mark-attendance")
	public ResponseEntity<?> markAttendance(@RequestBody String email){
		System.out.println("It is working");
		AttendaceStatus markAttendance = attendanceServiceImpl.markAttendance(email);
		System.out.println(markAttendance);
		return ResponseEntity.ok(markAttendance);
		
	}
	
	
	
	

}
