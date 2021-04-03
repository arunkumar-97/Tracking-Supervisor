package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.List;

import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;

public interface AttendanceService {

	Attendance save(Attendance userData);

	List<Attendance> findAllByDateBetween(Date fromDateTime, Date toDateTime);

	List<Attendance> findAllByUserAndDateBetween(User user, Date fromDate, Date toDate);

	Attendance findByUserStatus(String string);

	Attendance findByUserStatusAndUser(String string, User user);

	List<Attendance> findAll();

	List<Attendance> findAllByUser(User user);

	List<Attendance> findAllByDateAndAddress(Date date, String address);

	List<Attendance> findAllByDateAndAddressAndUser(Date date, String address, User user);



}
