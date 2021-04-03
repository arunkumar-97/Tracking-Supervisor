package com.jesperapps.tracksupervisor.api.entity;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.message.UserRequestEntity;

public class UserResEntity extends BaseResponse{
	private UserRequestEntity data;
	
	private Integer statusCode;
	private String description;
	private Integer errorCode;
	
	public UserResEntity() {
		super();
	}

	public UserResEntity(UserRequestEntity userReqEntity) {
		this.data = userReqEntity;
	}

	public UserRequestEntity getData() {
		return data;
	}

	public void setData(UserRequestEntity data) {
		this.data = data;
	}
	
}
