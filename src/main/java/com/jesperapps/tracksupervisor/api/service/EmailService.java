package com.jesperapps.tracksupervisor.api.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.jesperapps.tracksupervisor.api.model.ConfirmationToken;
import com.jesperapps.tracksupervisor.api.model.User;

public interface EmailService {

	public void sendEmail(SimpleMailMessage email);

	
	public boolean sendOTPMail(User user1);


	ConfirmationToken getConfirmationTokenForUser(User user);


	boolean checkOTP(User user, String requestOTP);


	public boolean sendOTPMail(List<User> user);


}
