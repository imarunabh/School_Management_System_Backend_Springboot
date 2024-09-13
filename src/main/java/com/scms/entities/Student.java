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
@Data
@Table(name = "student")
public class Student {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String name;
	
	private String S_Class;
	
	private String BloodGroup;
	
	private String FatherName;
	
	private String MotherName;
	
	private Date dob;
	
	private int Age;
	
	private String Address;
	
	private UserRole role=UserRole.STUDENT;
	
	private String image;

	@Override
	public String toString() {
		return "Student [Id=" + Id + ", email=" + email + ", password=" + password + ", name=" + name + ", S_Class="
				+ S_Class + ", BloodGroup=" + BloodGroup + ", FatherName=" + FatherName + ", MotherName=" + MotherName
				+ ", dob=" + dob + ", Age=" + Age + ", Address=" + Address + ", role=" + role + ", image=" + image
				+ "]";
	}

	
	
	
	
	

}
