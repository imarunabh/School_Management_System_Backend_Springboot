package com.scms.services.attendance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scms.entities.Attendance;
import com.scms.enums.AttendaceStatus;
import com.scms.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	
	@Autowired
	private AttendanceRepository attendanceRepository;

	

	@Override
	public List<Attendance> getAttendance(String email) {
		List<Attendance> findAttendanceByEmail = attendanceRepository.findAttendanceByEmail(email);
		return findAttendanceByEmail;
	}



	@Override
	public AttendaceStatus markAttendance(String email) {
		int count = attendanceRepository.Check(email, new Date());
		
		System.out.println(count);
		if(count>0){
			System.out.println("Is Already Present");
			return AttendaceStatus.PRESENT;
		}
		else {
		Attendance  attendance = new Attendance();
		attendance.setEmail(email);
		attendance.setAtt(AttendaceStatus.PRESENT);
		attendance.setDate(new Date());
		attendanceRepository.save(attendance);
		return attendance.getAtt();
		}
		
	}

}
