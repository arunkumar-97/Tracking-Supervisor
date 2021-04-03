package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;

public class DoNotTrakResponseEntity extends BaseResponse {

	
	private Integer trackId;
	private Status status;
	private ApprovalStatus approvalStatus;
	private Date fromDate;
	private Date toDate;
	private User user;
	
	
	public DoNotTrakResponseEntity() {
		
	}
	
	public DoNotTrakResponseEntity(DoNotTrack dontrack) {
		// TODO Auto-generated constructor stub
		
		this.trackId=dontrack.getTrackId();
		this.status=dontrack.getStatus();
		this.approvalStatus=dontrack.getApprovalstatus();
		this.fromDate=dontrack.getFromDate();
		this.toDate=dontrack.getToDate();
		this.user=dontrack.getUser();
	}
	public DoNotTrakResponseEntity(DoNotTrackRequestingEntity userReqEntity) {
		// TODO Auto-generated constructor stub
		this.trackId=userReqEntity.getTrackId();
		this.status=userReqEntity.getStatus();
		this.approvalStatus=userReqEntity.getApprovalStatus();
		this.fromDate=userReqEntity.getFromDate();
		this.toDate=userReqEntity.getToDate();
		this.user=userReqEntity.getUser();
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
	
	
	
	
	
}
