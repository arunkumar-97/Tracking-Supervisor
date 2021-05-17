package com.jesperapps.tracksupervisor.api.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jesperapps.tracksupervisor.api.message.WorkPlaceRequestEntity;

@Entity
public class WorkPlace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workPlaceId;
	private Date fromDate;
	private Date toDate;
	private Time startTime;
	private Time endTime;
	private String timeInterval;

	@JsonIgnore
	@OneToMany(mappedBy = "workPlace", cascade = CascadeType.ALL)
	private List<Address> address;

	@ManyToOne
	@JoinColumn
	private User assignedFromUser;

	@ManyToOne
	@JoinColumn
	private User assignedToUser;

	@ManyToOne
	@JoinColumn
	private Status status;
	
	
	@ManyToOne
	@JoinColumn(name="locationDetails_Id")
	private LocationDetails locationDetails;

	public WorkPlace() {

	}

	public WorkPlace(WorkPlaceRequestEntity workPlaceReqEntity) {
		super();
		this.workPlaceId = workPlaceReqEntity.getWorkPlaceId();
		this.fromDate = workPlaceReqEntity.getFromDate();
		this.toDate = workPlaceReqEntity.getToDate();
		this.assignedFromUser = workPlaceReqEntity.getAssignedFromUser();
		this.assignedToUser = workPlaceReqEntity.getAssignedToUser();
		this.address = workPlaceReqEntity.getAddress();
		this.address.forEach(x -> x.setWorkPlace(this));
		this.status = workPlaceReqEntity.getStatus();
		this.startTime=workPlaceReqEntity.getStartTime();
		this.endTime=workPlaceReqEntity.getEndTime();
		this.timeInterval=workPlaceReqEntity.getTimeInterval();
		this.locationDetails=workPlaceReqEntity.getLocationDetails();
	}

	public WorkPlace(WorkPlace workPlace) {
		super();
		this.workPlaceId = workPlace.getWorkPlaceId();
		this.fromDate = workPlace.getFromDate();
		this.toDate = workPlace.getToDate();
		this.address = workPlace.getAddress();
		this.assignedFromUser = workPlace.getAssignedFromUser();
		this.assignedToUser = workPlace.getAssignedToUser();
		this.status = workPlace.getStatus();
	}
	
	

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
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

	public Long getWorkPlaceId() {
		return workPlaceId;
	}

	public void setWorkPlaceId(Long workPlaceId) {
		this.workPlaceId = workPlaceId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public User getAssignedFromUser() {
		return assignedFromUser;
	}

	public void setAssignedFromUser(User assignedFromUser) {
		this.assignedFromUser = assignedFromUser;
	}

	public User getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(User assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocationDetails getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	}
	
	
	

//	@Override
//	public String toString() {
//		return "WorkPlace [workPlaceId=" + workPlaceId + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
//	}

}
