package com.jesperapps.tracksupervisor.api.message;

import java.util.Set;
import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;

public class UserTypeResponseEntity extends BaseResponse {

	private Long userTypeId;
	private String userTypeName;
	private String description;
	private String status;
	private Set<User> user;

	public UserTypeResponseEntity() {
	}

	public UserTypeResponseEntity(UserType userType) {
		super();
		this.userTypeId = userType.getUserTypeId();
		this.userTypeName = userType.getUserTypeName();
		this.description = userType.getDescription();
		this.status = userType.getStatus();
	}

	public UserTypeResponseEntity(UserType userType, UserType ut2) {
		this.userTypeId = userType.getUserTypeId();
		this.userTypeName = userType.getUserTypeName();
		this.description = userType.getDescription();
		this.status = userType.getStatus();
	}

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

	public String setDescription(String description) {
		return this.description = description;
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
		return "UserTypeResponseEntity [userTypeId=" + userTypeId + ", userTypeName=" + userTypeName + ", description="
				+ description + ", status=" + status + "]";
	}

}
