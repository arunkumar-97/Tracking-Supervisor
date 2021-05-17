package com.jesperapps.tracksupervisor.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.jesperapps.tracksupervisor.api.model.ConfirmationToken;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.ConfirmationTokenRepository;
import com.jesperapps.tracksupervisor.api.repository.UserRepository;

@Service
public class EmailServiceImpl implements EmailService {

//	@Autowired
//	private JavaMailSender mailSender;
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	private final Integer OTP_LENGTH = 6;
//	private final String FROM_ADDRESS = "rose.pauline@jespersoft.com";
//	@Async
//	public void sendEmail(SimpleMailMessage email) {
//		mailSender.send(email);
//	}
//
//	
//	private String generateRandomOTP() {
//		String oneTimePassword = "";
//		Random randomGenerator = new Random();
//		for(int i=0;i<OTP_LENGTH;i++) {
//			oneTimePassword += randomGenerator.nextInt(10);
//		}
//		return oneTimePassword;
//	}
//	
//
//	@Override
//	public ConfirmationToken getConfirmationTokenForUser(User user) {
//		String newOtp = this.generateRandomOTP();
//		ConfirmationToken newToken = user.getOtpToken() != null ? user.getOtpToken() : new ConfirmationToken(newOtp, user);
//		newToken.setConfirmationToken(newOtp);
//		user.setOtpToken(newToken);
//		userRepository.save(user);
//		return newToken;
//	}
//	
//	@Override
//	public boolean checkOTP(User user, String requestOTP) {
//		if(user.getOtpToken() != null) {
//			if(user.getOtpToken().getConfirmationToken().equals(requestOTP)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	
//	
//	
//	@Override
//	public boolean sendOTPMail(User otpUser) {
//		// 		TODO Auto-generated method stub
//		System.out.println("otpUser"+otpUser);
//		System.out.println("otpUser.getEmail()"+otpUser.getEmail());
//		try {
////			SimpleMailMessage otpMail = new SimpleMailMessage();
////			otpMail.setTo(otpUser.getEmail());
////			ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(otpUser);
////			otpMail.setFrom(FROM_ADDRESS);
////			otpMail.setText("Hi "+otpUser.getName()+", The One Time Password for your login request is "+oneTimePassword.getConfirmationToken());
////			this.mailSender.send(otpMail);
//			
//			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "mail.jespersoft.com");
//			props.put("mail.smtp.port", "25");
//
//
//			Authenticator auth = new Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(FROM_ADDRESS, "Jesper$2020");
//				}
//			};
//			Session session = Session.getInstance(props, auth);
//			Message msg = new MimeMessage(session);
//			msg.setFrom(new InternetAddress(FROM_ADDRESS, false));
//			List<InternetAddress> list = new ArrayList<InternetAddress>();
//			
//				InternetAddress to1 = new InternetAddress(otpUser.getEmail());
//				msg.setRecipient(Message.RecipientType.TO, to1);
//				list.add(to1);
//			
//			Address[] addressTo = list.toArray(new Address[] {});
//			msg.setRecipients(Message.RecipientType.TO, addressTo);
//			System.out.println("otpUser"+otpUser);
//			ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(otpUser);
//                    System.out.println("oneTimePassword.getConfirmationToken()"+oneTimePassword.getConfirmationToken());
//			msg.setSubject("OTP FOR LOGIN");
//			msg.setText("Hi "+otpUser.getName()+","
//					
//					+ " Use OTP "+oneTimePassword.getConfirmationToken() + " to login to your Educatizzy Account.Educatizzy doesn't ask for OTP or Contact number to be shared with anyone including Educatizzy Personnel");
//			
//			
//			msg.setSentDate(new Date());
////			List<InternetAddress> listOfToAddress = new ArrayList<InternetAddress>();
////			for (String cc : emailReqEntity2.getCc()) {
////				InternetAddress cc1 = new InternetAddress(cc);
////				msg.setRecipient(Message.RecipientType.CC, cc1);
////				listOfToAddress.add(cc1);
////			}
////			Address[] address = listOfToAddress.toArray(new Address[] {});
////
////			msg.setRecipients(Message.RecipientType.CC, address);		
//			msg.saveChanges();
//			Transport.send(msg);
//		}catch(Exception e){
//			System.out.println("mail exception "+e.getLocalizedMessage());
//			return false;
//		}
//		return true;
//	}
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unused")
	private JavaMailSender javaMailSender;
	private final Integer OTP_LENGTH = 6;
	private final String FROM_ADDRESS = "arun.kumar@jespersoft.com";
//	private final String FROM_ADDRESS = "arun.thril@gmail.com";
	
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	

	

	public EmailServiceImpl() {
		
	}

	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	private String generateRandomOTP() {
		String oneTimePassword = "";
		Random randomGenerator = new Random();
		for(int i=0;i<OTP_LENGTH;i++) {
			oneTimePassword += randomGenerator.nextInt(10);
		}
		return oneTimePassword;
	}
	

	@Override
	public ConfirmationToken getConfirmationTokenForUser(User user) {
		String newOtp = this.generateRandomOTP();
		ConfirmationToken newToken = user.getOtpToken() != null ? user.getOtpToken() : new ConfirmationToken(newOtp, user);
		System.out.println("Otp Token" + newToken);
		newToken.setConfirmationToken(newOtp);
		user.setOtpToken(newToken);
		userRepository.save(user);
		return newToken;
	}
	
	@Override
	public boolean checkOTP(User user, String requestOTP) {
		if(user.getOtpToken() != null) {
			if(user.getOtpToken().getConfirmationToken().equals(requestOTP)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean sendOTPMail(User otpUser) {
		// 		TODO Auto-generated method stub
		
		System.out.println("OtpUser" + otpUser.toString() );
		try {
//			SimpleMailMessage otpMail = new SimpleMailMessage();
//			otpMail.setTo(otpUser.getEmail());
//			ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(otpUser);
//			otpMail.setFrom(FROM_ADDRESS);
//			otpMail.setText("Hi "+otpUser.getUserName()+", The One Time Password for your login request is "+oneTimePassword.getConfirmationToken());
//			this.javaMailSender.send(otpMail);
			
			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
////			props.put("mail.smtp.host", "smtp.gmail.com");
//			props.put("mail.smtp.host", "mail.jespersoft.com");
//			props.put("mail.smtp.port", "465");
			
			
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "mail.jespersoft.com");
//			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "25");


			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(FROM_ADDRESS,"Jesper$2021");
//					return new PasswordAuthentication(FROM_ADDRESS,"arunvenkat");
				}
			};
			Session session = Session.getInstance(props, auth);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM_ADDRESS, false));
			List<InternetAddress> list = new ArrayList<InternetAddress>();
			
				InternetAddress to1 = new InternetAddress(otpUser.getEmail());
				msg.setRecipient(Message.RecipientType.TO, to1);
				list.add(to1);
			
			Address[] addressTo = list.toArray(new Address[] {});
			msg.setRecipients(Message.RecipientType.TO, addressTo);
	
			ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(otpUser);
			System.out.println("Confirmation Token for User :" + this.getConfirmationTokenForUser(otpUser) );
			System.out.println("One" + oneTimePassword);
			System.out.println("Check");
			System.out.println("One Time Password" + oneTimePassword);
			msg.setSubject("OTP FOR LOGIN");
			msg.setText("Hi "+otpUser.getName()+","
					
					+ " Use OTP "+ oneTimePassword.getConfirmationToken() + " to login to your Printlok Account.Printlok doesn't ask for OTP or Contact number to be shared with anyone including Printlok Personnel");
			
			
			msg.setSentDate(new Date());
//			List<InternetAddress> listOfToAddress = new ArrayList<InternetAddress>();
//			for (String cc : emailReqEntity2.getCc()) {
//				InternetAddress cc1 = new InternetAddress(cc);
//				msg.setRecipient(Message.RecipientType.CC, cc1);
//				listOfToAddress.add(cc1);
//			}
//			Address[] address = listOfToAddress.toArray(new Address[] {});
//
//			msg.setRecipients(Message.RecipientType.CC, address);		
			msg.saveChanges();
			Transport.send(msg);
		}
		catch(Exception e) {
			System.out.println("Exception :" + e.getMessage());
			return false;
		}
		return true;
	}

	
	
	
	@Override
	public void sendEmail(SimpleMailMessage email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sendOTPMail(List<User> user) {
		
		
		for(User each:user) {
			System.out.println("OtpUser" + each.toString() );
			try {
//				SimpleMailMessage otpMail = new SimpleMailMessage();
//				otpMail.setTo(otpUser.getEmail());
//				ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(otpUser);
//				otpMail.setFrom(FROM_ADDRESS);
//				otpMail.setText("Hi "+otpUser.getUserName()+", The One Time Password for your login request is "+oneTimePassword.getConfirmationToken());
//				this.javaMailSender.send(otpMail);
				
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "25");

				Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(FROM_ADDRESS,"arunvenkat");
					}
				};
				Session session = Session.getInstance(props, auth);
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(FROM_ADDRESS, false));
				List<InternetAddress> list = new ArrayList<InternetAddress>();
				
					InternetAddress to1 = new InternetAddress(each.getEmail());
					msg.setRecipient(Message.RecipientType.TO, to1);
					list.add(to1);
				
				Address[] addressTo = list.toArray(new Address[] {});
				msg.setRecipients(Message.RecipientType.TO, addressTo);
		
				ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(each);
				System.out.println("Confirmation Token for User :" + this.getConfirmationTokenForUser(each) );
				System.out.println("One" + oneTimePassword);
				System.out.println("Check");
				System.out.println("One Time Password" + oneTimePassword);
				msg.setSubject("OTP FOR LOGIN");
				msg.setText("Hi "+each.getName()+","
						
						+ " Use OTP "+ oneTimePassword.getConfirmationToken() + " to login to your Educatizzy Account.Educatizzy doesn't ask for OTP or Contact number to be shared with anyone including Educatizzy Personnel");
				
				
				msg.setSentDate(new Date());
//				List<InternetAddress> listOfToAddress = new ArrayList<InternetAddress>();
//				for (String cc : emailReqEntity2.getCc()) {
//					InternetAddress cc1 = new InternetAddress(cc);
//					msg.setRecipient(Message.RecipientType.CC, cc1);
//					listOfToAddress.add(cc1);
//				}
//				Address[] address = listOfToAddress.toArray(new Address[] {});
	//
//				msg.setRecipients(Message.RecipientType.CC, address);		
				msg.saveChanges();
				Transport.send(msg);
			}
			catch(Exception e) {
				System.out.println("Exception :" + e.getMessage());
				return false;
			}
		}
		// TODO Auto-generated method stub
	
		return true;
	}

	
	
	
	
}
