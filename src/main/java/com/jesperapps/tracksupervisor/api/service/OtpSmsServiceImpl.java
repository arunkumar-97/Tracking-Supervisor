package com.jesperapps.tracksupervisor.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.OtpSms;
import com.jesperapps.tracksupervisor.api.repository.OtpSmsRepository;

@Service
public class OtpSmsServiceImpl implements OtpSmsService {

	@Autowired
	private OtpSmsRepository otpSmsRepository;
	
	
	
	
	private static final Integer EXPIRE_MINS = 7;

	@Override
	public int generateOTP(String phoneNumber) {
		System.out.println("ph "+ phoneNumber);
		try {
			OtpSms otpFromDb = this.otpSmsRepository.findByPhoneNumber(phoneNumber);
			System.out.println("otpFromDb" + otpFromDb);
			Random random = new Random();
			int otp = 100000 + random.nextInt(900000);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String otpGenerationTime = dtf.format(now);
			OtpSms otpSms;
			if(otpFromDb == null){
				otpSms = new OtpSms(phoneNumber, otp, EXPIRE_MINS, otpGenerationTime);				
			}else {
				otpSms = otpFromDb;
				otpSms.setOtp(otp);
				otpSms.setExpireMins(EXPIRE_MINS);
				otpSms.setOtpGenerationTime(otpGenerationTime);
			}
			@SuppressWarnings("unused")
			OtpSms otpSmsSaved = otpSmsRepository.save(otpSms);
			return otp;
		} catch (Exception e) {
			System.out.println("Exc" +e.getLocalizedMessage());
			return 0;
		}

	}

	@Override
	public OtpSms clearOTP(String phone) {
		System.out.println("test claeR");
		try {
			 System.out.println("try claer");
			 System.out.println("phone :" + phone );
			return this.deleteByPhoneNumber(phone);
			 
//			 System.out.println("otpSms"+otpSms);
			 
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		
	}

	@Override
	public List<OtpSms> findAllByPhoneNumber(String phone) {
		// TODO Auto-generated method stub
		return otpSmsRepository.findAllByPhoneNumber(phone);
	}

	@Override
	public List<OtpSms> findAll() {
		// TODO Auto-generated method stub
		return otpSmsRepository.findAll();
	}
	
	public OtpSms deleteByPhoneNumber(String phoneNumber) {
		return otpSmsRepository.deleteByPhoneNumber(phoneNumber);
	}

	
	
	@Override
	public OtpSms getOtp(String phoneNumber) {
		System.out.println("phoneNumber" + phoneNumber);
		try {
			OtpSms sms = this.findByPhoneNumber(phoneNumber);
			System.out.println("sms" + sms);
			return sms;
		} catch (Exception e) {
			
			return null;
		}

	}
	
	@Override
	public OtpSms findByPhoneNumber(String phoneNumber) {
		return otpSmsRepository.findByPhoneNumber(phoneNumber);
	}

	
	

	
}
