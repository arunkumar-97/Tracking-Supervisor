package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Status {

	@Id
	private Long statusId;
	private String statusName;

	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<WorkPlace> workPlace;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<Organization> organization;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<Address> address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<DoNotTrack> track;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<FreeTrial> freeTrial;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<OrganizationFreeTrial> organizationFreeTrial;
	

	public Status() {
		// TODO Auto-generated constructor stub
	}

	public Status(Status status2) {
		super();
		this.statusId = status2.getStatusId();
		this.statusName = status2.getStatusName();
	}

	public Status(String string) {
		this.statusId = (long) 2;
		this.statusName = "InActive";
	}

	public Status(Long l) {
		this.statusId = l;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<WorkPlace> getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(List<WorkPlace> workPlace) {
		this.workPlace = workPlace;
	}

	public List<Organization> getOrganization() {
		return organization;
	}

	public void setOrganization(List<Organization> organization) {
		this.organization = organization;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<DoNotTrack> getTrack() {
		return track;
	}

	public void setTrack(List<DoNotTrack> track) {
		this.track = track;
	}

	public List<FreeTrial> getFreeTrial() {
		return freeTrial;
	}

	public void setFreeTrial(List<FreeTrial> freeTrial) {
		this.freeTrial = freeTrial;
	}
	
	

}
