package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {
	
	@Id
	private Integer countryId;
	private String countryName;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<State> state;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<Organization> organization;
	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
	
	

}
