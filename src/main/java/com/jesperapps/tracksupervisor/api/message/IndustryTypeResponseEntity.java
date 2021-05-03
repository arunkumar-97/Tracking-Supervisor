package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.IndustryType;

public class IndustryTypeResponseEntity extends BaseResponse{
	
	private Integer industryTypeId;
	private String IndustryTypeName;
	private String status;
	
	public IndustryTypeResponseEntity() {
		
	}
	
	
	public IndustryTypeResponseEntity(IndustryType industryType) {
		this.industryTypeId=industryType.getIndustryTypeId();
		this.IndustryTypeName=industryType.getIndustryTypeName();
		this.status=industryType.getStatus();
		
	}
	public Integer getIndustryTypeId() {
		return industryTypeId;
	}
	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}
	public String getIndustryTypeName() {
		return IndustryTypeName;
	}
	public void setIndustryTypeName(String industryTypeName) {
		IndustryTypeName = industryTypeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
