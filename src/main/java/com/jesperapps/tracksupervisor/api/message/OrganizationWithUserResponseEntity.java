package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;

public class OrganizationWithUserResponseEntity extends BaseResponse{
	
	private OrganizationRequestEntity organization;
	private UserRequestEntity user;
	
	
	
	public OrganizationRequestEntity getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationRequestEntity organization) {
		this.organization = organization;
	}
	public UserRequestEntity getUser() {
		return user;
	}
	public void setUser(UserRequestEntity user) {
		this.user = user;
	}
	
	
	

}
