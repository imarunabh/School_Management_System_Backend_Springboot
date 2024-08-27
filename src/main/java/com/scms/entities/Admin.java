package com.scms.entities;

import com.scms.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="admin")
@Data
public class Admin {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String Address;
	
	private String post_School;
	
	private UserRole role=UserRole.ADMIN;
	
	private String BloodGroup;

}
