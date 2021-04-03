package com.jesperapps.tracksupervisor.api.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jesperapps.tracksupervisor.api.extra.AbstractAuditingEntity;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackRequestingEntity;

@Entity
public class DoNotTrack extends AbstractAuditingEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer trackId;
	private Date fromDate;
	private Date toDate;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_Id",referencedColumnName = "userId")
	private User user;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Status status;

	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private ApprovalStatus approvalstatus;
	
	public DoNotTrack() {
		
	}
	
	
	public DoNotTrack(DoNotTrackRequestingEntity reqEntity) {
		// TODO Auto-generated constructor stub
		this.trackId=reqEntity.getTrackId();
		this.fromDate=reqEntity.getFromDate();
		this.toDate=reqEntity.getToDate();
		this.status=reqEntity.getStatus();
		this.approvalstatus=reqEntity.getApprovalStatus();
		this.user=reqEntity.getUser();
	}
	public DoNotTrack(Integer trackId2, Status status2, Date fromDate2, Date toDate2, LocalDateTime createDateTime,
			LocalDateTime updateDateTime,ApprovalStatus approvalStatus2,User user2) {
		// TODO Auto-generated constructor stub
		this.trackId=trackId2;
		this.status=status2;
		this.fromDate=fromDate2;
		this.toDate=toDate2;
		this.setCreateDateTime(createDateTime);
		this.setUpdateDateTime(updateDateTime);
		this.approvalstatus=approvalStatus2;
		this.user=user2;
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
	public ApprovalStatus getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(ApprovalStatus approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	
	
	
	
	
}
