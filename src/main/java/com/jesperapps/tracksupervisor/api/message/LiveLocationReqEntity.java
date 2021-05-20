package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.User;

public class LiveLocationReqEntity {
	
	private Integer liveLocationId;
	private double latitude;
	private double longitude;
	private User user;
	
	
	public Integer getLiveLocationId() {
		return liveLocationId;
	}
	public void setLiveLocationId(Integer liveLocationId) {
		this.liveLocationId = liveLocationId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
