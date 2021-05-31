package com.jesperapps.tracksupervisor.api.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jesperapps.tracksupervisor.api.model.Status;

public class FreeTrialReqEntity {
	
	private Integer freeTrialId;
	public String freeTrialName;
	private Integer noOfDays;
	private Status status;
	@JsonProperty
	private boolean isDefault;

	
	
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
