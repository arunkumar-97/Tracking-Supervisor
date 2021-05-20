package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.model.User;

public class LiveLocationResEntity extends BaseResponse{

	
	private Integer liveLocationId;
	private double latitude;
	private double longitude;
	private User user;
	
	
	
	public LiveLocationResEntity() {
		
	}
	public LiveLocationResEntity(LiveLocation user2) {
		this.liveLocationId=user2.getLiveLocationId();
		this.latitude=user2.getLatitude();
		this.longitude=user2.getLongitude();
		this.user=user2.getUser();
	}
	public LiveLocationResEntity(LiveLocationResEntity user2) {
		this.liveLocationId=user2.getLiveLocationId();
		this.latitude=user2.getLatitude();
		this.longitude=user2.getLongitude();
		this.user=user2.getUser();
	}
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
