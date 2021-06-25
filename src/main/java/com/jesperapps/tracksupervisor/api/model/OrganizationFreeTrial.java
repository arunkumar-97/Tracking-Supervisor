package com.jesperapps.tracksupervisor.api.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrganizationFreeTrial {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer organizationFreeTrialId;
	
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	private Organization organization;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	
	@ManyToOne
	@JoinColumn
	private FreeTrial freeTrial;
	
	
	private Date startDate;
	private Date endDate;
	
	@ManyToOne
	@JoinColumn
	private Status status;

	public OrganizationFreeTrial() {
		
	}
	
	public OrganizationFreeTrial(OrganizationFreeTrial organizationFreeTrial) {
		this.organizationFreeTrialId=organizationFreeTrial.getOrganizationFreeTrialId();
		this.startDate=organizationFreeTrial.getStartDate();
		this.endDate=organizationFreeTrial.getEndDate();
		this.freeTrial=organizationFreeTrial.getFreeTrial();
	}

	public Integer getOrganizationFreeTrialId() {
		return organizationFreeTrialId;
	}

	public void setOrganizationFreeTrialId(Integer organizationFreeTrialId) {
		this.organizationFreeTrialId = organizationFreeTrialId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public FreeTrial getFreeTrial() {
		return freeTrial;
	}

	public void setFreeTrial(FreeTrial freeTrial) {
		this.freeTrial = freeTrial;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
