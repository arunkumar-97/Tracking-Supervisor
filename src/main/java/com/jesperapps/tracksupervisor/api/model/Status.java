package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Status {

	@Id
	private Long statusId;
	private String statusName;

	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<WorkPlace> workPlace;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<Organization> organization;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<Address> address;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<DoNotTrack> track;
	

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
	
	

}
