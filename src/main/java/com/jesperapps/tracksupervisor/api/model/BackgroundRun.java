package com.jesperapps.tracksupervisor.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jesperapps.tracksupervisor.api.message.BackGroundRunReqEntity;

@Entity
public class BackgroundRun {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer backroundRunId;
	private String location;
	
	public BackgroundRun() {
		
	}
	public BackgroundRun(BackGroundRunReqEntity reqEntity) {
		this.backroundRunId=reqEntity.getBackroundRunId();
		this.location=reqEntity.getLocation();
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
