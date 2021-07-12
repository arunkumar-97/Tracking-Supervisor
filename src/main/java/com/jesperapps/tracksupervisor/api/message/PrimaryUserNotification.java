package com.jesperapps.tracksupervisor.api.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.User;

public class PrimaryUserNotification {
	
	private User priUser,
	secUser;
	private DoNotTrack dontTrack;
	
	public PrimaryUserNotification(User primaryUser, User secUser, DoNotTrack doNotTrack){
		this.priUser = primaryUser;
		this.secUser = secUser;
		this.dontTrack = doNotTrack;
	}
	
	public String getTitle() {
		return this.secUser.getName() + " has requested to don't track";
	}
	
	public String getBody() {
		return "From " + this.dontTrack.getFromDate()+" to "+ this.dontTrack.getToDate();
	}
	
	public String getData() throws JsonProcessingException {
		ObjectMapper jsonConverter = new ObjectMapper();
		return jsonConverter.writeValueAsString(this.dontTrack);
	}
}