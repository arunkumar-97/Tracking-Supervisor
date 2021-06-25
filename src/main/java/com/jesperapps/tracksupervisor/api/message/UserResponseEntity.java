package com.jesperapps.tracksupervisor.api.message;

import java.util.List;
import java.util.Set;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.OrganizationFreeTrial;
import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;

public class UserResponseEntity extends BaseResponse{

	private Long userId;
	private String name;
	private Long passcode;
	private String phoneNumber;
	private String email;
	private String password;
	private String alternatePhoneNumber;
	private String status;
	private String userStatus;
	private String address;
	private String postalCode;
	private State states;
	private Country country;
	private City city;
	private Long createdByUser;
	private Set<UserType> userType;
	private Attachment attachment;
	private Set<Attendance> userData;
	private Set<Attendance> attendance;
	private List<TimeTracking> timeTracking;
	private Organization organization;
	private FreeTrial freeTrial;
	private OrganizationFreeTrial organizationFreeTrial;

	
	public UserResponseEntity() {

	}

	public UserResponseEntity(UserRequestEntity userRequestEntity) {
		super();
		this.userId = userRequestEntity.getUserId();
		this.name = userRequestEntity.getName();
		this.passcode = userRequestEntity.getPasscode();
		this.phoneNumber = userRequestEntity.getPhoneNumber();
		this.email = userRequestEntity.getEmail();
		this.alternatePhoneNumber = userRequestEntity.getAlternatePhoneNumber();
		this.status = userRequestEntity.getStatus();
		this.userType = userRequestEntity.getUserType();
		this.attachment = userRequestEntity.getAttachment();
		this.createdByUser = userRequestEntity.getCreatedByUser();
		this.organization = userRequestEntity.getOrganization();
		this.organizationFreeTrial=userRequestEntity.getOrganizationFreeTrial();
		this.freeTrial=userRequestEntity.getFreeTrial();
	}



	public UserResponseEntity(User userRequestEntity) {
		this.userId = userRequestEntity.getUserId();
		this.name = userRequestEntity.getName();
		this.passcode = userRequestEntity.getPasscode();
		this.phoneNumber = userRequestEntity.getPhoneNumber();
		this.email = userRequestEntity.getEmail();
		this.alternatePhoneNumber = userRequestEntity.getAlternatePhoneNumber();
		this.status = userRequestEntity.getStatus();
		if(userRequestEntity.getAttachment() == null) {
			
		}else {
			this.attachment = userRequestEntity.getAttachment();
		}
		
		this.attendance = userRequestEntity.getAttendance();
	}

	public UserResponseEntity(User userDatas, User userDatas2) {
		
	this.userId = userDatas.getUserId();
	this.userStatus = userDatas.getUserStatus();
	this.name = userDatas.getName();
//	this.passcode = userDatas.getPasscode();
	this.phoneNumber = userDatas.getPhoneNumber();
	this.email = userDatas.getEmail();
	this.alternatePhoneNumber = userDatas.getAlternatePhoneNumber();
	this.status = userDatas.getStatus();
	this.userType = userDatas.getUserType();
	this.attachment = userDatas.getAttachment();
	this.createdByUser = userDatas.getCreatedByUser();
	this.city=userDatas.getCity();
	this.states=userDatas.getStates();
	this.country=userDatas.getCountry();
	this.address=userDatas.getAddress();
	this.postalCode=userDatas.getPostalCode();
	if(userDatas.getOrganization() != null) {
		this.organization = userDatas.getOrganization();
	}else {
		this.organization =new Organization();
	}
	
	}
	
	
	
	

	public UserResponseEntity(SecondaryUser each, User secondaryUser) {
		this.userId = secondaryUser.getUserId();
		this.userType = secondaryUser.getUserType();
		this.createdByUser = secondaryUser.getCreatedByUser();
		this.timeTracking  = secondaryUser.getTimeTracking();
		this.phoneNumber=secondaryUser.getPhoneNumber();
		this.alternatePhoneNumber=secondaryUser.getAlternatePhoneNumber();
		this.city=secondaryUser.getCity();
		this.states=secondaryUser.getStates();
		this.country=secondaryUser.getCountry();
		this.postalCode=secondaryUser.getPostalCode();
		this.address=secondaryUser.getAddress();
		this.organization = secondaryUser.getOrganization();
		this.email = secondaryUser.getEmail();
		this.name = secondaryUser.getName();
		this.createdByUser = secondaryUser.getCreatedByUser();
	}

	public UserResponseEntity(String name2) {
		// TODO Auto-generated constructor stub
		this.name=name2;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	public Set<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(Set<Attendance> attendance) {
		this.attendance = attendance;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Long getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
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

	public FreeTrial getFreeTrial() {
		return freeTrial;
	}

	public void setFreeTrial(FreeTrial freeTrial) {
		this.freeTrial = freeTrial;
	}
	
	

	public OrganizationFreeTrial getOrganizationFreeTrial() {
		return organizationFreeTrial;
	}

	public void setOrganizationFreeTrial(OrganizationFreeTrial organizationFreeTrial) {
		this.organizationFreeTrial = organizationFreeTrial;
	}

	@Override
	public String toString() {
		return "UserResponseEntity [userId=" + userId + ", name=" + name + ", passcode=" + passcode + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", alternatePhoneNumber=" + alternatePhoneNumber + ", status="
				+ status + "]";
	}
	
	
}
