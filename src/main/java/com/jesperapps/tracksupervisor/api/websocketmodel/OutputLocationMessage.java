package com.jesperapps.tracksupervisor.api.websocketmodel;

import com.jesperapps.tracksupervisor.api.utils.ApprovalsStatus;

public class OutputLocationMessage extends OutputMessage {
	
	private double latitude = 0.0;
	private double longitude =0.0;

	public OutputLocationMessage() {
		super(ApprovalsStatus.ACCEPTED);
	}
	public OutputLocationMessage(String message) {
		super(message);
		// TODO Auto-generated constructor stub
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
	public void updateLocation(double lat, double longi) {
		this.setLatitude(lat);
		this.setLongitude(longi);
	}

}
