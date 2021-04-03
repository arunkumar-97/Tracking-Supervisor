package com.jesperapps.tracksupervisor.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp_sms")
public class OtpSms {

	@Id
	@Column(name = "otp_sms_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long otpSmsId;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "otp")
	private int otp;

	@Column(name = "expire_mins")
	private int expireMins;

	@Column(name = "otp_generation_time")
	private String otpGenerationTime;

	public OtpSms() {
		super();
	}

	public OtpSms(String phoneNumber, int otp, Integer expireMins, String otpGenerationTime) {
		this.phoneNumber = phoneNumber;
		this.otp = otp;
		this.expireMins = expireMins;
		this.otpGenerationTime = otpGenerationTime;
	}

	
	public long getOtpSmsId() {
		return otpSmsId;
	}

	public void setOtpSmsId(long otpSmsId) {
		this.otpSmsId = otpSmsId;
	}

	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public int getExpireMins() {
		return expireMins;
	}

	public void setExpireMins(int expireMins) {
		this.expireMins = expireMins;
	}

	public String getOtpGenerationTime() {
		return otpGenerationTime;
	}

	public void setOtpGenerationTime(String otpGenerationTime) {
		this.otpGenerationTime = otpGenerationTime;
	}

	
	

}
