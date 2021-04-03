package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;
import java.sql.Time;

import com.jesperapps.tracksupervisor.api.model.User;

public class TimeTrackingRequestEntity {
	
	private Long timeTrackingId;
	private Date date;
	private Time startTime;
	private Time endTime;
	private String timeInterval;
	private String status;
	private User user;
	
	public Long getTimeTrackingId() {
		return timeTrackingId;
	}
	public void setTimeTrackingId(Long timeTrackingId) {
		this.timeTrackingId = timeTrackingId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
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
	
	

}
