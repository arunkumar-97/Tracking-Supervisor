package com.jesperapps.tracksupervisor.api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	
	List<Attendance> findAllByDateBetween(Date fromDateTime, Date toDateTime);

	List<Attendance> findByUser(User user);

	List<Attendance> findAllByUserAndDateBetween(User user, Date fromDate, Date toDate);

	Attendance findByUserStatus(String string);

	Attendance findByUserStatusAndUser(String string, User user);

	List<Attendance> findAllByUser(User user);

	List<Attendance> findAllByDateAndAddress(Date date, String address);

	List<Attendance> findAllByDateAndAddressAndUser(Date date, String address, User user);



}
