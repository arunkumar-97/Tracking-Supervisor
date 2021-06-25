package com.jesperapps.tracksupervisor.api.message;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.OrganizationFreeTrial;
import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.UserType;

public class UserRequestEntity {
	
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
	private String otp;
	private OrganizationFreeTrial organizationFreeTrial;

	private FreeTrial freeTrial;

	public UserRequestEntity() {
	
	}

	public UserRequestEntity(User user) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.passcode = user.getPasscode();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.alternatePhoneNumber = user.getAlternatePhoneNumber();
		this.userType = user.getUserType();
		this.timeTracking = user.getTimeTracking();
	}

	

	public UserRequestEntity(User user, Long userId2) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.passcode = user.getPasscode();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.alternatePhoneNumber = user.getAlternatePhoneNumber();
		this.userType = user.getUserType();
		if(user.getAttachment() == null) {
		}else {
			this.attachment = user.getAttachment();
		}
		this.createdByUser = user.getCreatedByUser();
		this.organization = user.getOrganization();
		this.organizationFreeTrial=user.getOrganizationFreeTrial();

	
	}

	public UserRequestEntity(Optional<User> user2, User user, Long userId2) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.passcode = user.getPasscode();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.alternatePhoneNumber = user.getAlternatePhoneNumber();
		this.userType = user.getUserType();
		if(user.getAttachment() == null) {
		}else {
			this.attachment = user.getAttachment();
		}
		this.createdByUser = user.getCreatedByUser();
		this.organization = user.getOrganization();
		this.organizationFreeTrial=user.getOrganizationFreeTrial();
		
	}

	public UserRequestEntity(User userData2, User userData3) {
		this.userId = userData2.getUserId();
		this.userType = userData2.getUserType();
		this.createdByUser = userData2.getCreatedByUser();
		this.timeTracking  = userData2.getTimeTracking();
		this.organization = userData2.getOrganization();
		this.email = userData2.getEmail();
		this.name = userData2.getName();
		this.createdByUser = userData2.getCreatedByUser();
	}
	
	
	

	public UserRequestEntity(SecondaryUser each, User secondaryUser) {
		this.userId = secondaryUser.getUserId();
		this.userType = secondaryUser.getUserType();
		this.createdByUser = secondaryUser.getCreatedByUser();
		this.timeTracking  = secondaryUser.getTimeTracking();
		this.phoneNumber=secondaryUser.getPhoneNumber();
		this.alternatePhoneNumber=secondaryUser.getAlternatePhoneNumber();
		this.city=secondaryUser.getCity();
		this.states=secondaryUser.getState();
		this.country=secondaryUser.getCountry();
		this.postalCode=secondaryUser.getPostalCode();
		this.address=secondaryUser.getAddress();
		this.organization = secondaryUser.getOrganization();
		this.email = secondaryUser.getEmail();
		this.name = secondaryUser.getName();
		this.createdByUser = secondaryUser.getCreatedByUser();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Attendance> getUserData() {
		return userData;
	}

	public void setUserData(Set<Attendance> userData) {
		this.userData = userData;
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
	
	
	
	public String getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}
	
	

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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
		return "UserRequestEntity [userId=" + userId + ", name=" + name + ", passcode=" + passcode + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", alternatePhoneNumber=" + alternatePhoneNumber + "]";
	}
	
	
}
