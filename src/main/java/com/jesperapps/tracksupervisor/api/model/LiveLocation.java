package com.jesperapps.tracksupervisor.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.jesperapps.tracksupervisor.api.message.LiveLocationReqEntity;

@Entity
public class LiveLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer liveLocationId;
	private double latitude;
	private double longitude;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_Id")
	private User user;
	
	public LiveLocation() {
		
	}
	
	public LiveLocation(LiveLocationReqEntity liveLocationRequestEntity) {
		this.liveLocationId=liveLocationRequestEntity.getLiveLocationId();
		this.latitude=liveLocationRequestEntity.getLatitude();
		this.longitude=liveLocationRequestEntity.getLongitude();
		this.user=liveLocationRequestEntity.getUser();
		
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
