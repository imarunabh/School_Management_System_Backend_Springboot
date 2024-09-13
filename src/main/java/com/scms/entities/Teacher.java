package com.scms.entities;

import java.util.Date;

import com.scms.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(unique = true)
	private String email;
	
	private String name;
	
	private String password;
	
	private Long salary;
	
	private String Address;
	
	private Date dob;
	
	private int age;
	
	private String bloodGroup;
	
	private UserRole  role=UserRole.TEACHER;
	
	private String image;

}
