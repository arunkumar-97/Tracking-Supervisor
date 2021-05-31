package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.BackgroundRun;

public class BackGroundRunResEntity extends BaseResponse {
	
	private Integer backroundRunId;
	private String location;
	
	public BackGroundRunResEntity() {
		
	}
	public BackGroundRunResEntity(BackgroundRun backgroundRun) {
		this.backroundRunId=backgroundRun.getBackroundRunId();
		this.location=backgroundRun.getLocation();
	}
	public Integer getBackroundRunId() {
		return backroundRunId;
	}
	public void setBackroundRunId(Integer backroundRunId) {
		this.backroundRunId = backroundRunId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	

}
