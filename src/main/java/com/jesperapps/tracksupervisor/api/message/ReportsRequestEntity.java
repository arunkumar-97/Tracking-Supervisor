package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;
import java.sql.Time;

import com.jesperapps.tracksupervisor.api.model.User;

public class ReportsRequestEntity {
	
	private Integer reportId;
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
	
	
	
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
