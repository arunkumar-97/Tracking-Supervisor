package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.model.OtpSms;



public interface OtpSmsService {
	
	
	int generateOTP(String string);

	OtpSms clearOTP(String phoneNumber);

	List<OtpSms> findAllByPhoneNumber(String phone);

	List<OtpSms> findAll();

	OtpSms getOtp(String phoneNumber);

	OtpSms findByPhoneNumber(String phoneNumber);

}
