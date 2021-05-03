package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;
import java.sql.Time;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;

public class AttendanceResponseEntity extends BaseResponse {

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


	public AttendanceResponseEntity() {

	}

	//list attendance by date
	public AttendanceResponseEntity(Attendance att1) {
		super();
		this.attendanceId = att1.getAttendanceId();
		this.date = att1.getDate();
		this.time = att1.getTime();
		this.latitude = att1.getLatitude();
		this.longitude = att1.getLongitude();
		this.noOfWorkers = att1.getNoOfWorkers();
		this.day = att1.getDay();
		this.address = att1.getAddress();
		this.status = att1.getStatus();
		this.user = att1.getUser();
	}


	public AttendanceResponseEntity(Attendance attendanceDatas, Attendance attendanceDatas2) {
		super();
		this.attendanceId = attendanceDatas.getAttendanceId();
		this.date = attendanceDatas.getDate();
		this.time = attendanceDatas.getTime();
		this.latitude = attendanceDatas.getLatitude();
		this.longitude = attendanceDatas.getLongitude();
		this.noOfWorkers = attendanceDatas.getNoOfWorkers();
		this.day = attendanceDatas.getDay();
		this.address = attendanceDatas.getAddress();
		this.status = attendanceDatas.getStatus();
		this.userStatus = attendanceDatas.getUserStatus();
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
		return "AttendanceResponseEntity [attendanceId=" + attendanceId + ", date=" + date + ", time=" + time
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", noOfWorkers=" + noOfWorkers + ", day="
				+ day + ", address=" + address + ", status=" + status + "]";
	}

}