package com.jesperapps.tracksupervisor.api.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jesperapps.tracksupervisor.api.extra.AbstractAuditingEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationRequestEntity;
import com.jesperapps.tracksupervisor.api.message.UserRequestEntity;

@Entity
@Table(name = "user")
public class User extends AbstractAuditingEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(columnDefinition = "bigint(20)")
	private Long createdByUser;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_usertype_mapping", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	@JoinColumn(name = "userTypeId_id") })
	private Set<UserType> userType;
	
	@JsonManagedReference("user_attachment")
	@OneToOne(mappedBy = "user", orphanRemoval = true,
    cascade = CascadeType.ALL)
	private Attachment attachment;

	@OneToMany(mappedBy = "user")
	private Set<Attendance> attendance;
	
	@OneToMany(mappedBy = "assignedFromUser")
	private List<WorkPlace> workPlace;
	
	@OneToMany(mappedBy = "secondaryUser")
	private Set<SecondaryUser> SecondaryUser;
	
	@OneToMany(mappedBy = "primaryUser")
	private Set<SecondaryUser> primaryUser;
	
	@OneToMany(mappedBy = "assignedToUser")
	private List<WorkPlace> workPlacee;
	
	@OneToMany(mappedBy = "noOfWorkersUpdatedByUser")
	private List<WorkersCount> workersCount;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user")
	private List<TimeTracking> timeTracking;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Organization organization;
	
	@OneToMany(mappedBy = "user")
	private List<Reports> reports;

	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<DoNotTrack> track;
	
	public User() {
		super();
	}

	// user update
	public User(Long userId2, UserRequestEntity userRequestEntity) {
		this.userId = userRequestEntity.getUserId();
		this.name = userRequestEntity.getName();
		this.phoneNumber = userRequestEntity.getPhoneNumber();
		this.email = userRequestEntity.getEmail();
		this.alternatePhoneNumber = userRequestEntity.getAlternatePhoneNumber();
		this.userType = userRequestEntity.getUserType();
		if (userRequestEntity.getAttachment() == null) {

		} else {
			this.attachment = userRequestEntity.getAttachment();
			this.attachment.setUser(this);
		}
		this.createdByUser = userRequestEntity.getCreatedByUser();
		this.organization = userRequestEntity.getOrganization();
	}

	//user create
	public User(UserRequestEntity userRequestEntity, UserRequestEntity userRequestEntity2) {
		this.userId = userRequestEntity.getUserId();
		this.name = userRequestEntity.getName();
		this.phoneNumber = userRequestEntity.getPhoneNumber();
		this.email = userRequestEntity.getEmail();
		this.password=userRequestEntity.getPassword();
		this.alternatePhoneNumber = userRequestEntity.getAlternatePhoneNumber();
		this.userType = userRequestEntity.getUserType();
		if (userRequestEntity.getAttachment() == null) {

		} else {
			this.attachment = userRequestEntity.getAttachment();
			this.attachment.setUser(this);
		}
		this.createdByUser = userRequestEntity.getCreatedByUser();
		this.timeTracking = userRequestEntity.getTimeTracking();
		this.timeTracking.forEach(x -> x.setUser(this));
		this.organization = userRequestEntity.getOrganization();
	}

	public User(Long passcode2) {
		this.passcode = passcode2;
	}

	public User(Long userId2, Long userId3) {
		this.userId = userId2;
	}

	public User(User user) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.passcode = user.getPasscode();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.alternatePhoneNumber = user.getAlternatePhoneNumber();
		this.status = user.getStatus();
		this.userType = user.getUserType();
		this.attachment = user.getAttachment();
		this.attendance = user.getAttendance();
//		this.userStatus = userStatus;
	}

	public User(String phoneNumber2, String phoneNumber3) {
		this.phoneNumber = phoneNumber2;
	}

	public User(User user, String userStatus) {
		super();
		this.userId = user.getUserId();
		this.name = user.getName();
		this.passcode = user.getPasscode();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.alternatePhoneNumber = user.getAlternatePhoneNumber();
		this.status = user.getStatus();
		this.userType = user.getUserType();
		this.attachment = user.getAttachment();
		this.attendance = user.getAttendance();
		this.userStatus = userStatus;
	}

	public User(User user, String userStatus2, String userStatus3) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.passcode = user.getPasscode();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.alternatePhoneNumber = user.getAlternatePhoneNumber();
		this.status = user.getStatus();
		this.userType = user.getUserType();
		this.attachment = user.getAttachment();
		this.attendance = user.getAttendance();
		this.userStatus = userStatus2;
		this.attendance = user.getAttendance();
	}

	public User(User user, Long userId2) {
		super();
		this.userId = userId2;
	}

	public User(User eachUser, User eachUser2, OrganizationRequestEntity organizationRequestEntity) {
		this.userId = eachUser.getUserId();
		this.name = eachUser.getName();
		this.phoneNumber = eachUser.getPhoneNumber();
		this.email = eachUser.getEmail();
		this.password=eachUser.getPassword();
		this.alternatePhoneNumber = eachUser.getAlternatePhoneNumber();
		this.userType = eachUser.getUserType();
		if (eachUser.getAttachment() == null) {

		} else {
			this.attachment = eachUser.getAttachment();
			this.attachment.setUser(this);
		}
		this.createdByUser = eachUser.getCreatedByUser();
		this.timeTracking = eachUser.getTimeTracking();
		   if(eachUser.getTimeTracking() !=  null)
		   {
			   this.timeTracking.forEach(x -> x.setUser(this));
		   }
		
		this.organization = eachUser.getOrganization();
	}
	
	
	
	

	public Set<SecondaryUser> getSecondaryUser() {
		return SecondaryUser;
	}

	public void setSecondaryUser(Set<SecondaryUser> secondaryUser) {
		SecondaryUser = secondaryUser;
	}

	public Set<SecondaryUser> getPrimaryUser() {
		return primaryUser;
	}

	public void setPrimaryUser(Set<SecondaryUser> primaryUser) {
		this.primaryUser = primaryUser;
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

	public List<WorkPlace> getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(List<WorkPlace> workPlace) {
		this.workPlace = workPlace;
	}

	public List<WorkPlace> getWorkPlacee() {
		return workPlacee;
	}

	public void setWorkPlacee(List<WorkPlace> workPlacee) {
		this.workPlacee = workPlacee;
	}

	public List<WorkersCount> getWorkersCount() {
		return workersCount;
	}

	public void setWorkersCount(List<WorkersCount> workersCount) {
		this.workersCount = workersCount;
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

	
	
	public List<Reports> getReports() {
		return reports;
	}

	public void setReports(List<Reports> reports) {
		this.reports = reports;
	}

	public List<DoNotTrack> getTrack() {
		return track;
	}

	public void setTrack(List<DoNotTrack> track) {
		this.track = track;
	}

//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", name=" + name + ", passcode=" + passcode + ", phoneNumber=" + phoneNumber
//				+ ", email=" + email + ", alternatePhoneNumber=" + alternatePhoneNumber + ", status=" + status
//				+ ", userStatus=" + userStatus + ", createdByUser=" + createdByUser + ", userType=" + userType
//				+ ", attachment=" + attachment + ", attendance=" + attendance + ", workPlace=" + workPlace
//				+ ", workPlacee=" + workPlacee + ", workersCount=" + workersCount + ", timeTracking=" + timeTracking
//				+ ", organization=" + organization + "]";
//	}

	
}
