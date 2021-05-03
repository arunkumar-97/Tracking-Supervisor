package com.jesperapps.tracksupervisor.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class ConfirmationToken {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tokenId;
	private String confirmationToken;
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="userId")
//	private User user;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ConfirmationToken() {
		super();
	}

	public ConfirmationToken(String confirmationToken, User user) {
	
		this.confirmationToken = confirmationToken;
		this.user = user;
	}

	@Override
	public String toString() {
		return "ConfirmationToken [tokenId=" + tokenId + ", confirmationToken=" + confirmationToken + ", user=" + user
				+ "]";
	}

	
	



}
