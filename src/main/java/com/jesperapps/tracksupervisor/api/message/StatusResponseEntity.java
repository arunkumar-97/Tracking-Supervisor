package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.Status;

public class StatusResponseEntity {

	private Long statusId;
	private String statusName;

	public StatusResponseEntity() {

	}

	public StatusResponseEntity(Status status) {
		super();
		this.statusId = status.getStatusId();
		this.statusName = status.getStatusName();
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
