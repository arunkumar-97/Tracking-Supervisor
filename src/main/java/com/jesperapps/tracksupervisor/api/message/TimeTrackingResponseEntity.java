package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;
import java.sql.Time;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;

public class TimeTrackingResponseEntity extends BaseResponse {
	
	private Long timeTrackingId;
	private Date date;
	private Time startTime;
	private Time endTime;
	private String timeInterval;
	private String status;
	private User user;
	
	public TimeTrackingResponseEntity() {
		// TODO Auto-generated constructor stub
	}
	
	
	public TimeTrackingResponseEntity(TimeTracking t, TimeTracking t2) {
		super();
		this.timeTrackingId = t.getTimeTrackingId();
		this.date = t.getDate();
		this.startTime = t.getStartTime();
		this.endTime = t.getEndTime();
		this.timeInterval = t.getTimeInterval();
		this.status = t.getStatus();
		this.user = t.getUser();
	}


	public TimeTrackingResponseEntity(TimeTracking timeTracking) {
		this.timeTrackingId = timeTracking.getTimeTrackingId();
		this.date = timeTracking.getDate();
		this.startTime = timeTracking.getStartTime();
		this.endTime = timeTracking.getEndTime();
		this.timeInterval = timeTracking.getTimeInterval();
		this.status = timeTracking.getStatus();
		this.user = timeTracking.getUser();
	}


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
