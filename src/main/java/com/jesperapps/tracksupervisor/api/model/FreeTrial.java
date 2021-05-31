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
	public String freeTrialName;
	private Integer noOfDays;
	private boolean isDefault;
	
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
		this.freeTrialName=reqEntity.getFreeTrialName();
		this.noOfDays=reqEntity.getNoOfDays();
		this.status=reqEntity.getStatus();
		this.isDefault=reqEntity.isDefault();
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
	public List<OrganizationFreeTrial> getOrganizationFreeTrial() {
		return organizationFreeTrial;
	}
	public void setOrganizationFreeTrial(List<OrganizationFreeTrial> organizationFreeTrial) {
		this.organizationFreeTrial = organizationFreeTrial;
	}
	
	
	

}
