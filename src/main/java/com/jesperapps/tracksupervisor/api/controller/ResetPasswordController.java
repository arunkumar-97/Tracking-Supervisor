package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.message.UserRequestEntity;
import com.jesperapps.tracksupervisor.api.model.ConfirmationToken;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.EmailService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ResetPasswordController{

	
	@Autowired
	private UserService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private EmailService emailService;
	
	UserResEntity res = new UserResEntity();
	
	private final Integer OTP_LENGTH = 6;
	private final String FROM_ADDRESS = "arun.kumar@jespersoft.com";
//	private final String FROM_ADDRESS = "arun.thril@gmail.com";
	
	@PostMapping("/forgotpassword")
	public ResponseEntity forgotPassword(@RequestBody UserRequestEntity user, HttpServletRequest request) {
		 
		String UserEmail = user.getEmail();
		User usr = service.findUserByEmail(UserEmail);
		if (usr == null) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.setErrorCode(404));
			jsonObject.put("message", res.setDescription("Email does not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		} else {
//			Long code = 1000 + usr.getUserId();
//			String newCode=code.toString();
//			usr.setPassword(newPasswrd);
			
			String oneTimePassword="";
			Random randomGenerator = new Random();
			for(int i=0;i<OTP_LENGTH;i++) {
				oneTimePassword += randomGenerator.nextInt(10);
			}
			usr.setOtp(oneTimePassword);
			User userSaved=service.save(usr);
			if(userSaved == null) {
				ObjectNode jsonObject = objectMapper.createObjectNode();
				jsonObject.put("statusCode", res.SUCCESS);
				jsonObject.put("description", res.setDescription("Something went wrong.PleaseTry again later"));
				return new ResponseEntity(jsonObject, HttpStatus.CONFLICT);
			}else {
				try {
//					SimpleMailMessage otpMail = new SimpleMailMessage();
//					otpMail.setTo(otpUser.getEmail());
//					ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(otpUser);
//					otpMail.setFrom(FROM_ADDRESS);
//					otpMail.setText("Hi "+otpUser.getUserName()+", The One Time Password for your login request is "+oneTimePassword.getConfirmationToken());
//					this.javaMailSender.send(otpMail);
					
					Properties props = new Properties();
//					props.put("mail.smtp.auth", "true");
//					props.put("mail.smtp.starttls.enable", "true");
////					props.put("mail.smtp.host", "smtp.gmail.com");
//					props.put("mail.smtp.host", "mail.jespersoft.com");
//					props.put("mail.smtp.port", "465");
					
					
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "mail.jespersoft.com");
//					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", "25");


					Authenticator auth = new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(FROM_ADDRESS,"Jesper$2021");
//							return new PasswordAuthentication(FROM_ADDRESS,"arunvenkat");
						}
					};
					Session session = Session.getInstance(props, auth);
					Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress(FROM_ADDRESS, false));
					List<InternetAddress> list = new ArrayList<InternetAddress>();
					
						InternetAddress to1 = new InternetAddress(usr.getEmail());
						msg.setRecipient(Message.RecipientType.TO, to1);
						list.add(to1);
					
					Address[] addressTo = list.toArray(new Address[] {});
					msg.setRecipients(Message.RecipientType.TO, addressTo);
			
//					ConfirmationToken oneTimePassword = this.getConfirmationTokenForUser(otpUser);
//					System.out.println("Confirmation Token for User :" + this.getConfirmationTokenForUser(otpUser) );
//					System.out.println("One" + oneTimePassword);
//					System.out.println("Check");
//					System.out.println("One Time Password" + oneTimePassword);
					msg.setSubject("FORGOT PASSWORD LOGIN");
					msg.setText("Hi "+usr.getName()+","
							
							+ " Use OTP "+ oneTimePassword  + " to login to your Printlok Account.Printlok doesn't ask for OTP or Contact number to be shared with anyone including Printlok Personnel");
					
					
					msg.setSentDate(new Date());
//					List<InternetAddress> listOfToAddress = new ArrayList<InternetAddress>();
//					for (String cc : emailReqEntity2.getCc()) {
//						InternetAddress cc1 = new InternetAddress(cc);
//						msg.setRecipient(Message.RecipientType.CC, cc1);
//						listOfToAddress.add(cc1);
//					}
//					Address[] address = listOfToAddress.toArray(new Address[] {});
		//
//					msg.setRecipients(Message.RecipientType.CC, address);		
					msg.saveChanges();
					Transport.send(msg);
				}
				catch(Exception e) {
					System.out.println("Exception :" + e.getMessage());
					
				}
				
				
				ObjectNode jsonObject = objectMapper.createObjectNode();
				jsonObject.put("statusCode", res.SUCCESS);
				jsonObject.put("description", res.setDescription("A OTP is sent to your mail for Login"));
				return new ResponseEntity(jsonObject, HttpStatus.OK);
			}

			
			
			
			
			
//			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
//			passwordResetEmail.setFrom("track@jespersoft.com");
//			passwordResetEmail.setTo(user.getEmail());
//			passwordResetEmail.setSubject("New Password");
//			passwordResetEmail.setText(
//					" Hi " +usr.getName()+", This is your New Password for Login: " + usr.getPassword());
//			emailService.sendEmail(passwordResetEmail);
			
		}
	}
}
