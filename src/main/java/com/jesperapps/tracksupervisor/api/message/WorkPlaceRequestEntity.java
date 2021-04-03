package com.jesperapps.tracksupervisor.api.message;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;

public class WorkPlaceRequestEntity {

	private Long workPlaceId;
	private Date fromDate;
	private Date toDate;
	private User assignedFromUser;
	private User assignedToUser;
	private List<Address> address;
	private Status status;

	public Long getWorkPlaceId() {
		return workPlaceId;
	}

	public void setWorkPlaceId(Long workPlaceId) {
		this.workPlaceId = workPlaceId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public User getAssignedFromUser() {
		return assignedFromUser;
	}

	public void setAssignedFromUser(User assignedFromUser) {
		this.assignedFromUser = assignedFromUser;
	}

	public User getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(User assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "WorkPlaceRequestEntity [workPlaceId=" + workPlaceId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ "]";
	}

}
