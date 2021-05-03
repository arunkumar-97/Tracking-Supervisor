package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class IndustryType {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer industryTypeId;
	private String IndustryTypeName;
	private String status;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "industryType")
	private List<Organization> organization;
	
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
