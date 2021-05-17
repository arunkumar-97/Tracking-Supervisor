package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.User;

public class LocationDetailsResponseEntity extends BaseResponse {
	
	private Integer locationId;
	private Double latitude;
	private Double longitude;
	private String address;
	private Organization organization;
	private User user;
	
	
	public LocationDetailsResponseEntity() {
		
	}
	
	public LocationDetailsResponseEntity(LocationDetails location) {
		this.locationId=location.getLocationId();
		this.latitude=location.getLatitude();
		this.longitude=location.getLongitude();
		this.address=location.getAddress();
		this.organization=location.getOrganization();
		this.user=location.getUser();
	}
	public LocationDetailsResponseEntity(LocationDetailsResponseEntity location) {
		this.locationId=location.getLocationId();
		this.latitude=location.getLatitude();
		this.longitude=location.getLongitude();
		this.address=location.getAddress();
		this.organization=location.getOrganization();
//		this.user=location.getUser();
	}

	public LocationDetailsResponseEntity(LocationDetailsResponseEntity location, LocationDetailsResponseEntity user3, User userFromDb) {
		this.locationId=location.getLocationId();
		this.latitude=location.getLatitude();
		this.longitude=location.getLongitude();
		this.address=location.getAddress();
		this.organization=location.getOrganization();
		this.user=userFromDb;	
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
