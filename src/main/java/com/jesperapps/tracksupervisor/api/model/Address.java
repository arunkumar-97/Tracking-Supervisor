package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	private String addressName;
	private Double latitude;
	private Double longitude;
	@ManyToOne
	@JoinColumn
	private WorkPlace workPlace;

	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private List<WorkersCount> workersCount;

	@ManyToOne
	@JoinColumn
	private Status status;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(Address a) {
		super();
		this.addressId = a.getAddressId();
		this.addressName = a.getAddressName();
		this.latitude = a.getLatitude();
		this.longitude = a.getLongitude();
		// this.noOfWorkers = a.getNoOfWorkers();
		if (a.getWorkersCount() != null) {
			this.workersCount = a.getWorkersCount();
		}

	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public WorkPlace getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(WorkPlace workPlace) {
		this.workPlace = workPlace;
	}

	public List<WorkersCount> getWorkersCount() {
		return workersCount;
	}

	public void setWorkersCount(List<WorkersCount> workersCount) {
		this.workersCount = workersCount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressName=" + addressName + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
