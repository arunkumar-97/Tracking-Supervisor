package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.model.Status;

public class FreeTrialResEntity extends BaseResponse{
	
	
	private Integer freeTrialId;
	private Integer noOfDays;
	private Status status;
	
	public FreeTrialResEntity() {
		
	}
	
	
	public FreeTrialResEntity(FreeTrial freeTrial) {
		this.freeTrialId=freeTrial.getFreeTrialId();
		this.noOfDays=freeTrial.getNoOfDays();
		this.status=freeTrial.getStatus();
	
	}
	public Integer getFreeTrialId() {
		return freeTrialId;
	}
	public void setFreeTrialId(Integer freeTrialId) {
		this.freeTrialId = freeTrialId;
	}
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	

}
