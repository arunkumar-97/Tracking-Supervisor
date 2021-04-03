package com.jesperapps.tracksupervisor.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class SecondaryUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="primaryUser_Id")
	private User primaryUser;
	
	
	@ManyToOne
	@JoinColumn(name="secondaryUser_Id")
	private User secondaryUser;


	public User getPrimaryUser() {
		return primaryUser;
	}

	
	public void setPrimaryUser(User primaryUser) {
		this.primaryUser = primaryUser;
	}


	public User getSecondaryUser() {
		return secondaryUser;
	}


	public void setSecondaryUser(User secondaryUser) {
		this.secondaryUser= secondaryUser;
	}
	
	
	
	
	
	
}
