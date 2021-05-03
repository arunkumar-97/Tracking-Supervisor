package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.State;

public class StateResponseEntity extends BaseResponse {

	
	
	private Integer stateId;
	private String stateName;
	
	
	public StateResponseEntity() {
		
	}
	
	public StateResponseEntity(State state) {
		this.stateId=state.getStateId();
		this.stateName=state.getStateName();
	}
	

	

	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	
	
	
	
}
