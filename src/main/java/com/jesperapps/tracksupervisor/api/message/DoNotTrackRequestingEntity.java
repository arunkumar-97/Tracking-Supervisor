package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;

import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;

public class DoNotTrackRequestingEntity {
	
	private Integer trackId;
	private Status status;
	private ApprovalStatus approvalStatus;
	private Date fromDate;
	private Date toDate;
	private User user;
	
	public DoNotTrackRequestingEntity() {
		
	}
	
	
	public DoNotTrackRequestingEntity(DoNotTrack dontrackdata) {
		// TODO Auto-generated constructor stub
		this.trackId=dontrackdata.getTrackId();
		this.status=dontrackdata.getStatus();
		this.approvalStatus=dontrackdata.getApprovalstatus();
		this.fromDate=dontrackdata.getFromDate();
		this.toDate=dontrackdata.getToDate();
		this.user=dontrackdata.getUser();
	}
	public Integer getTrackId() {
		return trackId;
	}
	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	

}
