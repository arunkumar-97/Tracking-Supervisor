package com.jesperapps.tracksupervisor.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class City {
	
	@Id
	
	private Integer cityId;
	private String cityName;
	
	
	@ManyToOne
	@JoinColumn(name="state_Id",referencedColumnName = "stateId")
	private State state;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
	private List<Organization> organization;
	
	@OneToMany(mappedBy = "city")
	private List<User> user;
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	

}
