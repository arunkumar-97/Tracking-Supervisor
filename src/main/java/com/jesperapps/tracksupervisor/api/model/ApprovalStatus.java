package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ApprovalStatus {
	
	@Id
	private int approvalStatusId;
	private String approvalStatusName;
	
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<DoNotTrack> track;
	
	public ApprovalStatus() {
		
	}
	
	public ApprovalStatus(int i, String pending) {
		// TODO Auto-generated constructor stub
		this.approvalStatusId=i;
		this.approvalStatusName=pending;
	}
	public int getApprovalStatusId() {
		return approvalStatusId;
	}
	public void setApprovalStatusId(int approvalStatusId) {
		this.approvalStatusId = approvalStatusId;
	}
	public String getApprovalStatusName() {
		return approvalStatusName;
	}
	public void setApprovalStatusName(String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
	}
	
	
	
	

}
