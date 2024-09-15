package com.scms.entities;

import java.util.Date;

import com.scms.enums.AttendaceStatus;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="attendance")
@Data
public class Attendance{
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	
	private String email;
	
	private Date date;
	
	private AttendaceStatus att;

}
