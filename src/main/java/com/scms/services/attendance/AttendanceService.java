package com.scms.services.attendance;

import java.util.List;

import com.scms.entities.Attendance;
import com.scms.enums.AttendaceStatus;

public interface AttendanceService {
	
	
	public List<Attendance> getAttendance(String email);
	
	public AttendaceStatus markAttendance(String email);

}
