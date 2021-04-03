package com.jesperapps.tracksupervisor.api.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jesperapps.tracksupervisor.api.message.TimeTrackingRequestEntity;

@Entity
@Table(name = "time_tracking")
public class TimeTracking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long timeTrackingId;
	private Date date;
	private Time startTime;
	private Time endTime;
	private String timeInterval;
	private String status;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	public TimeTracking() {
	}

	public TimeTracking(TimeTrackingRequestEntity timeTrackingRequestEntity) {
		super();
		this.timeTrackingId = timeTrackingRequestEntity.getTimeTrackingId();
		this.date = timeTrackingRequestEntity.getDate();
		this.startTime = timeTrackingRequestEntity.getStartTime();
		this.endTime = timeTrackingRequestEntity.getEndTime();
		this.timeInterval = timeTrackingRequestEntity.getTimeInterval();
		this.status = timeTrackingRequestEntity.getStatus();
		this.user = timeTrackingRequestEntity.getUser();
	}

	public TimeTracking(TimeTracking timeTrackingRequestEntity) {
		this.timeTrackingId = timeTrackingRequestEntity.getTimeTrackingId();
		this.date = timeTrackingRequestEntity.getDate();
		this.startTime = timeTrackingRequestEntity.getStartTime();
		this.endTime = timeTrackingRequestEntity.getEndTime();
		this.timeInterval = timeTrackingRequestEntity.getTimeInterval();
		this.status = timeTrackingRequestEntity.getStatus();
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
