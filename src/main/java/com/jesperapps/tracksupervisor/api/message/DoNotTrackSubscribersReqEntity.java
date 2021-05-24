package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;

public class DoNotTrackSubscribersReqEntity {

	
	private Integer doNotTrackSubscribersId;
	private User user;
	private String subscriptionId;
	private Status status;
	
	public Integer getDoNotTrackSubscribersId() {
		return doNotTrackSubscribersId;
	}
	public void setDoNotTrackSubscribersId(Integer doNotTrackSubscribersId) {
		this.doNotTrackSubscribersId = doNotTrackSubscribersId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
