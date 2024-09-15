package com.scms.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.scms.entities.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	
	@Query(value="select * from Attendance u where u.email= :email",nativeQuery = true)
	List<Attendance> findAttendanceByEmail(String email);
	
	@Query(value="select count(u.email) from Attendance u where u.email = :email AND DAY(u.date) = DAY(:date) AND MONTH(u.date) = MONTH(:date)")
	int Check(String email,Date date);

}
