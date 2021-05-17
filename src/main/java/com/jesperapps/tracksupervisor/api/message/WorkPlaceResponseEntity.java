package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;

public class WorkPlaceResponseEntity extends BaseResponse{

	private Long workPlaceId;
	private Date fromDate;
	private Date toDate;
	private Time startTime;
	private Time endTime;
	private String timeInterval;
	private User assignedFromUser;
	private User assignedToUser;
	private List<Address> address;
	private Status status;
	private LocationDetails locationDetails;
	public WorkPlaceResponseEntity() {
		
	}
	
	
	public WorkPlaceResponseEntity(WorkPlace workPlace1) {
		super();
		this.workPlaceId = workPlace1.getWorkPlaceId();
		this.fromDate = workPlace1.getFromDate();
		this.toDate = workPlace1.getToDate();
		this.assignedFromUser = workPlace1.getAssignedFromUser();
		this.assignedToUser = workPlace1.getAssignedToUser();
		this.address = workPlace1.getAddress();
		this.status = workPlace1.getStatus();
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
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
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


	@Override
	public String toString() {
		return "WorkPlaceResponseEntity [workPlaceId=" + workPlaceId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ "]";
	}
	
	
	
}
