package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class State {
	
	
	@Id
	private Integer stateId;
	private String stateName;
	
	
	@ManyToOne
	@JoinColumn(name="country_Id",referencedColumnName = "countryId")
	private Country country;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "state")
	private List<City> city;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "states")
	private List<Organization> organization;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "states")
	private List<User> user;
	
	public State() {
		
	}
	
	public State(long numericCellValue) {
		// TODO Auto-generated constructor stub
		this.stateId=(int) numericCellValue;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	
	
	

}
