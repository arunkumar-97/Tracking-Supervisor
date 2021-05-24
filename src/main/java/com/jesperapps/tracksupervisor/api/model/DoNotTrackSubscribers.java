package com.jesperapps.tracksupervisor.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.jesperapps.tracksupervisor.api.message.DoNotTrackSubscribersReqEntity;

@Entity
public class DoNotTrackSubscribers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer doNotTrackSubscribersId;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_Id")
	private User user;
	
	private String subscriptionId;
	
	
	@ManyToOne
	@JoinColumn
	private Status status;
	
	
	public DoNotTrackSubscribers() {
		
	}

	public DoNotTrackSubscribers(DoNotTrackSubscribersReqEntity doNotTrackSubscribersReqEntity) {
		this.doNotTrackSubscribersId=doNotTrackSubscribersReqEntity.getDoNotTrackSubscribersId();
		this.user=doNotTrackSubscribersReqEntity.getUser();
		this.subscriptionId=doNotTrackSubscribersReqEntity.getSubscriptionId();
		this.status=doNotTrackSubscribersReqEntity.getStatus();
	}

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
