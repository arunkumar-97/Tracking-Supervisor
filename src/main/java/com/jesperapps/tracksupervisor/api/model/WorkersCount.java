package com.jesperapps.tracksupervisor.api.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WorkersCount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workerCountId;
	private int noOfWorkers;

	private Date date;

	@ManyToOne
	@JoinColumn
	private User noOfWorkersUpdatedByUser;

	@ManyToOne
	@JoinColumn
	private Address address;

	public WorkersCount() {
		// TODO Auto-generated constructor stub
	}
	
	

	public WorkersCount(int noOfWorkers2, Date date2, Address add, User noOfWorkersUpdatedBy) {
		super();
		this.noOfWorkers = noOfWorkers2;
		this.date = date2;
		this.noOfWorkersUpdatedByUser = noOfWorkersUpdatedBy;
		this.address = add;
	}

	public WorkersCount(WorkersCount wc) {
		super();
		this.workerCountId = wc.getWorkerCountId();
		this.noOfWorkers = wc.getNoOfWorkers();
		this.date = wc.getDate();
	}



	public Long getWorkerCountId() {
		return workerCountId;
	}

	public void setWorkerCountId(Long workerCountId) {
		this.workerCountId = workerCountId;
	}

	public int getNoOfWorkers() {
		return noOfWorkers;
	}

	public void setNoOfWorkers(int noOfWorkers) {
		this.noOfWorkers = noOfWorkers;
	}

	public User getNoOfWorkersUpdatedByUser() {
		return noOfWorkersUpdatedByUser;
	}

	public void setNoOfWorkersUpdatedByUser(User noOfWorkersUpdatedByUser) {
		this.noOfWorkersUpdatedByUser = noOfWorkersUpdatedByUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
