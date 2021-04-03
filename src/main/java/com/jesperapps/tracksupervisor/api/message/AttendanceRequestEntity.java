package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.User;

import java.sql.Date;
import java.sql.Time;

import com.jesperapps.tracksupervisor.api.model.Attendance;

public class AttendanceRequestEntity {

	private Long attendanceId;
	private Date date;
	private Time time;
	private Double latitude;
	private Double longitude;
	private Long noOfWorkers;
	private String day;
	private String address;
	private String status;
	private String userStatus;

	private User user;

	public AttendanceRequestEntity() {

	}

	public AttendanceRequestEntity(Attendance attendance) {
		super();
		this.attendanceId = attendance.getAttendanceId();
		this.date = attendance.getDate();
		this.time = attendance.getTime();
		this.latitude = attendance.getLatitude();
		this.longitude = attendance.getLongitude();
		this.noOfWorkers = attendance.getNoOfWorkers();
		this.status = attendance.getStatus();
		this.day = attendance.getDay();
		this.address = attendance.getAddress();
		this.user = attendance.getUser();
	}

	
	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getNoOfWorkers() {
		return noOfWorkers;
	}

	public void setNoOfWorkers(Long noOfWorkers) {
		this.noOfWorkers = noOfWorkers;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "AttendanceRequestEntity [attendanceId=" + attendanceId + ", date=" + date + ", time=" + time
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", noOfWorkers=" + noOfWorkers + ", day="
				+ day + ", address=" + address + ", status=" + status + "]";
	}

}
