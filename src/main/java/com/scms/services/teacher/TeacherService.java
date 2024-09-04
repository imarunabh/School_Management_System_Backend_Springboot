package com.scms.services.teacher;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.scms.entities.Teacher;

@Service
public interface TeacherService {
	
	Teacher getTeacherById(Long id);

}
