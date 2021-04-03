package com.jesperapps.tracksupervisor.api.message;

import java.util.Set;

import com.jesperapps.tracksupervisor.api.model.User;


public class UserTypeRequestEntity {

	private Long userTypeId;
	private String userTypeName;
	private String description;
	private String status;

	private Set<User> user;

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}



	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserTypeRequestEntity [userTypeId=" + userTypeId + ", userTypeName=" + userTypeName + ", description="
				+ description + ", status=" + status + "]";
	}

}
