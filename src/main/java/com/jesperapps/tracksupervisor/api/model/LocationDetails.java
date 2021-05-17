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

import com.jesperapps.tracksupervisor.api.message.LocationDetailsRequestEntity;

@Entity
public class LocationDetails {

	
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer locationId;
	private Double latitude;
	private Double longitude;
	private String address;
	
	@ManyToOne
	@JoinColumn(name="organization_Id")
	private Organization organization;

	@ManyToOne
	@JoinColumn(name="user_Id")
	private User user;
	
	@OneToMany(mappedBy = "locationDetails", cascade = CascadeType.ALL)
	private List<WorkPlace> workPlace;
	
	public LocationDetails() {
		
	}
	
	public LocationDetails(LocationDetailsRequestEntity reqEntity) {
		this.locationId=reqEntity.getLocationId();
		this.latitude=reqEntity.getLatitude();
		this.longitude=reqEntity.getLongitude();
		this.address=reqEntity.getAddress();
		this.organization=reqEntity.getOrganization();
		this.user=reqEntity.getUser();
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
