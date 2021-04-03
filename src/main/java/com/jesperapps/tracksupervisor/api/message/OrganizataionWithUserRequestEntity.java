package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.User;

public class OrganizataionWithUserRequestEntity {
	
	
	private OrganizationRequestEntity organization;
	private User user;
	
	
	public OrganizationRequestEntity getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationRequestEntity organization) {
		this.organization = organization;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	

}
