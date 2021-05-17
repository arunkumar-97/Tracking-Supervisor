package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jesperapps.tracksupervisor.api.message.FreeTrialReqEntity;

@Entity
public class FreeTrial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer freeTrialId;
	private Integer noOfDays;
	
	
	@ManyToOne
	@JoinColumn
	private Status status;


	@JsonIgnore
	@OneToMany(mappedBy = "freeTrial", cascade = CascadeType.ALL)
	private List<OrganizationFreeTrial> organizationFreeTrial;
	
	public FreeTrial() {
		
	}
	public FreeTrial(FreeTrialReqEntity reqEntity) {
		this.freeTrialId=reqEntity.getFreeTrialId();
		this.noOfDays=reqEntity.getNoOfDays();
		this.status=reqEntity.getStatus();
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
