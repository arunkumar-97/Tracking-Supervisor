package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.model.Status;

public class FreeTrialResEntity extends BaseResponse{
	
	
	private Integer freeTrialId;
	public String freeTrialName;
	private Integer noOfDays;
	private Status status;
	private boolean isDefault;

	
	public FreeTrialResEntity() {
		
	}
	
	
	public FreeTrialResEntity(FreeTrial freeTrial) {
		this.freeTrialId=freeTrial.getFreeTrialId();
		this.freeTrialName=freeTrial.getFreeTrialName();
		this.noOfDays=freeTrial.getNoOfDays();
		this.status=freeTrial.getStatus();
		this.isDefault=freeTrial.isDefault();
	
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


	public String getFreeTrialName() {
		return freeTrialName;
	}


	public void setFreeTrialName(String freeTrialName) {
		this.freeTrialName = freeTrialName;
	}


	public boolean isDefault() {
		return isDefault;
	}


	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	
	

}
