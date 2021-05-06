package com.jesperapps.tracksupervisor.api.model;

import java.io.Serializable;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jesperapps.tracksupervisor.api.extra.AbstractAuditingEntity;
import com.jesperapps.tracksupervisor.api.message.UserTypeRequestEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_type")
public class UserType extends AbstractAuditingEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_type_id", updatable = false, nullable = false)
	private Long userTypeId;

	@Column(name = "user_type_name", updatable = false)
	private String userTypeName;

	@Column(name = "description", updatable = false)
	private String description;

	@Column(name = "status")
	private String status;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "userType")
	private Set<User> user;

	public UserType() {

	}

	public UserType(UserTypeRequestEntity userTypeRequestEntity) {
		super();
		this.userTypeId = userTypeRequestEntity.getUserTypeId();
		this.userTypeName = userTypeRequestEntity.getUserTypeName();
		this.description = userTypeRequestEntity.getDescription();
		this.status = userTypeRequestEntity.getStatus();
	}

	public UserType(UserType userTypeRequestEntity) {
		this.userTypeId = userTypeRequestEntity.getUserTypeId();
		this.userTypeName = userTypeRequestEntity.getUserTypeName();
		this.description = userTypeRequestEntity.getDescription();
		this.status = userTypeRequestEntity.getStatus();
	}



	public UserType(UserType ut, Long userTypeId2) {
		this.userTypeId = ut.getUserTypeId();
		this.userTypeName = ut.getUserTypeName();
	}

	public UserType(UserType ut, UserType ut2) {
		this.userTypeId = ut.getUserTypeId();
		this.userTypeName = ut.getUserTypeName();
	}

	

	public UserType(long numericCellValue) {
		this.userTypeId=numericCellValue;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserType [userTypeId=" + userTypeId + ", userTypeName=" + userTypeName + ", description=" + description
				+ ", status=" + status + "]";
	}

}
