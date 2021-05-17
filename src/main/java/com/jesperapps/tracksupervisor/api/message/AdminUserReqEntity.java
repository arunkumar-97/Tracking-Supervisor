package com.jesperapps.tracksupervisor.api.message;

import java.util.List;
import java.util.Set;

import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.UserType;

public class AdminUserReqEntity {
	
	
	private Long userId;
	private String name;
	private Long passcode;
	private String phoneNumber;
	private String email;
	private String password;
	private String alternatePhoneNumber;
	private String status;
	private String userStatus;
	private String authenticationType;
	private String address;
	private String postalCode;
	private State states;
	private Country country;
	private City city;
	private Long createdByUser;
	private Set<UserType> userType;
	private Attachment attachment;
	private Set<Attendance> userData;
	private List<TimeTracking> timeTracking;
	private Organization organization;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPasscode() {
		return passcode;
	}
	public void setPasscode(Long passcode) {
		this.passcode = passcode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}
	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
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
	public Long getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}
	public Set<UserType> getUserType() {
		return userType;
	}
	public void setUserType(Set<UserType> userType) {
		this.userType = userType;
	}
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public Set<Attendance> getUserData() {
		return userData;
	}
	public void setUserData(Set<Attendance> userData) {
		this.userData = userData;
	}
	public List<TimeTracking> getTimeTracking() {
		return timeTracking;
	}
	public void setTimeTracking(List<TimeTracking> timeTracking) {
		this.timeTracking = timeTracking;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
	
	

}
