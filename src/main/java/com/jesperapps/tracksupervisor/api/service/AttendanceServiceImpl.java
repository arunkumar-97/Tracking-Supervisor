package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService{

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Override
	public Attendance save(Attendance userData) {
		return attendanceRepository.save(userData);
	}

	@Override
	public List<Attendance> findAllByDateBetween(Date fromDateTime, Date toDateTime) {
		return attendanceRepository.findAllByDateBetween(fromDateTime, toDateTime);
	}


	@Override
	public List<Attendance> findAllByUserAndDateBetween(User user, Date fromDate, Date toDate) {
		return attendanceRepository.findAllByUserAndDateBetween(user, fromDate, toDate);
	}

	@Override
	public Attendance findByUserStatus(String string) {
		return attendanceRepository.findByUserStatus(string);
	}

	@Override
	public Attendance findByUserStatusAndUser(String string, User user) {
		return attendanceRepository.findByUserStatusAndUser(string, user);
	}

	@Override
	public List<Attendance> findAll() {
		return attendanceRepository.findAll();
	}

	@Override
	public List<Attendance> findAllByUser(User user) {
		return attendanceRepository.findAllByUser(user);
	}

	@Override
	public List<Attendance> findAllByDateAndAddress(Date date, String address) {
		return attendanceRepository.findAllByDateAndAddress(date, address);
	}

	@Override
	public List<Attendance> findAllByDateAndAddressAndUser(Date date, String address, User user) {
		// TODO Auto-generated method stub
		return attendanceRepository.findAllByDateAndAddressAndUser(date, address, user);
	}


}
