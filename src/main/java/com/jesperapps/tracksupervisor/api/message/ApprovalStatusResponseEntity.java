package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;

public class ApprovalStatusResponseEntity {

	private int approvalStatusId;
	private String approvalStatusName;
	
	
	public ApprovalStatusResponseEntity() {
		
	}
	
	
	public ApprovalStatusResponseEntity(ApprovalStatus userType) {
		// TODO Auto-generated constructor stub
		this.approvalStatusId=userType.getApprovalStatusId();
		this.approvalStatusName=userType.getApprovalStatusName();
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
