package com.jesperapps.tracksupervisor.api.extra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public abstract class BaseResponse {

	public static final int SUCCESS = 200;
	public static final int FAILURE = 400;
	//@JsonProperty(access = Access.WRITE_ONLY)
	private Integer statusCode;
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String description;
	//@JsonProperty(access = Access.WRITE_ONLY)
	private Integer errorCode;
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String message;
	
	public BaseResponse() {
		
	}
	
	public BaseResponse(Integer code, String description2) {
		// TODO Auto-generated constructor stub
		this.statusCode=code;
		this.description=description2;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public Integer setStatusCode(Integer statusCode) {
		return this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public String setDescription(String description) {
		return this.description = description;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public Integer setErrorCode(Integer errorCode) {
		return this.errorCode = errorCode;
	}

	public static int getSuccess() {
		return SUCCESS;
	}

	public static int getFailure() {
		return FAILURE;
	}

	public String getMessage() {
		return message;
	}

	public String setMessage(String message) {
		return this.message = message;
	}

	@Override
	public String toString() {
		return "BaseResponse [statusCode=" + statusCode + ", description=" + description + ", errorCode=" + errorCode
				+ "]";
	}

}
