package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.Country;

public class CountryResponseEntity {

	
	private Integer countryId;
	private String countryName;
private String countryNiceName;
	
	private String iso;
	private String iso3;
	private Integer numCode;
	private Integer phoneCode;
	
	public CountryResponseEntity() {
		
	}
	
	public CountryResponseEntity(Country country) {
	this.countryId=country.getCountryId();
	this.countryName=country.getCountryName();
	this.countryNiceName=country.getCountryNiceName();
	this.iso=country.getIso();
	this.iso3=country.getIso3();
	this.numCode=country.getNumCode();
	this.phoneCode=country.getPhoneCode();
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

	public String getCountryNiceName() {
		return countryNiceName;
	}

	public void setCountryNiceName(String countryNiceName) {
		this.countryNiceName = countryNiceName;
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
	
	
	
}
