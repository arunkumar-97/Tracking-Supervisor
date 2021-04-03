package com.jesperapps.tracksupervisor.api.message;

import java.util.List;
import java.util.Set;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.Organization;
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
	
	private Long createdByUser;

	private Set<UserType> userType;
	private Attachment attachment;
	private Set<Attendance> userData;
	private Set<Attendance> attendance;
	private List<TimeTracking> timeTracking;
	
	private Organization organization;

	
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

	@Override
	public String toString() {
		return "UserResponseEntity [userId=" + userId + ", name=" + name + ", passcode=" + passcode + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", alternatePhoneNumber=" + alternatePhoneNumber + ", status="
				+ status + "]";
	}
	
	
}
