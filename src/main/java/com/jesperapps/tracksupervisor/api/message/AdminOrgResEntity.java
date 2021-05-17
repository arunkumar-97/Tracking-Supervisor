package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.IndustryType;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;

public class AdminOrgResEntity extends BaseResponse {
	
	private Integer organizationId;
	private String organizationName;
	private IndustryType industryType;
	private String contactPerson;
	private String contactCountryCode;
	private String contactPhoneNumber;
	private String contactEmail;
	private String address;
	private String postalCode;
	private String fiscalYearStart;
	private String defaulTimezone;
	private String reportingCurrency;
	private State states;
	private Country country;
	private City city;
	private User user;
	private Status status;
	private String gstNumber;
	
	public AdminOrgResEntity() {
		
	}
	
	public AdminOrgResEntity(Organization createdOrganization) {
		this.organizationId=createdOrganization.getOrganizationId();
		this.organizationName=createdOrganization.getOrganizationName();
		this.contactEmail=createdOrganization.getContactEmail();
		this.contactPhoneNumber=createdOrganization.getContactPhoneNumber();
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public IndustryType getIndustryType() {
		return industryType;
	}
	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactCountryCode() {
		return contactCountryCode;
	}
	public void setContactCountryCode(String contactCountryCode) {
		this.contactCountryCode = contactCountryCode;
	}
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}
	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getFiscalYearStart() {
		return fiscalYearStart;
	}
	public void setFiscalYearStart(String fiscalYearStart) {
		this.fiscalYearStart = fiscalYearStart;
	}
	public String getDefaulTimezone() {
		return defaulTimezone;
	}
	public void setDefaulTimezone(String defaulTimezone) {
		this.defaulTimezone = defaulTimezone;
	}
	public String getReportingCurrency() {
		return reportingCurrency;
	}
	public void setReportingCurrency(String reportingCurrency) {
		this.reportingCurrency = reportingCurrency;
	}
	public State getStates() {
		return states;
	}
	public void setStates(State states) {
		this.states = states;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	
	

}
