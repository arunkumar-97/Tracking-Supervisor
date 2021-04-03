package com.jesperapps.tracksupervisor.api.entity;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.message.AttendanceRequestEntity;

public class AttendanceResEntity extends BaseResponse{
	
	private AttendanceRequestEntity data;
	
	private Integer statusCode;
	private String description;
	private Integer errorCode;
	
	public AttendanceResEntity() {
		
	}
	
	
	public AttendanceResEntity(AttendanceRequestEntity userDataReqEntity) {
		super();
		this.data = userDataReqEntity;
	}


	public AttendanceRequestEntity getData() {
		return data;
	}
	public void setData(AttendanceRequestEntity data) {
		this.data = data;
	}
	
	
}
