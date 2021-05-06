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
	private String countryNiceName;
	
	private String iso;
	private String iso3;
	private Integer numCode;
	private Integer phoneCode;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<State> state;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<Organization> organization;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<User> user;
	
	
	public Country() {
		super();
	}
	public Country(long numericCellValue) {
		// TODO Auto-generated constructor stub
		this.countryId=(int) numericCellValue;
	}
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
	
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public String getIso3() {
		return iso3;
	}
	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public Integer getNumCode() {
		return numCode;
	}
	public void setNumCode(Integer numCode) {
		this.numCode = numCode;
	}
	public Integer getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(Integer phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getCountryNiceName() {
		return countryNiceName;
	}
	public void setCountryNiceName(String countryNiceName) {
		this.countryNiceName = countryNiceName;
	}
	
	
	
	

}
