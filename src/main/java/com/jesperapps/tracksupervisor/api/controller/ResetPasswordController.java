package com.jesperapps.tracksupervisor.api.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.EmailService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@RestController
public class ResetPasswordController {

	
	@Autowired
	private UserService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private EmailService emailService;
	
	UserResEntity res = new UserResEntity();
	
	@PostMapping("forgotpassword")
	public ResponseEntity forgotPassword(@RequestBody User user, HttpServletRequest request) {
		 
		String UserEmail = user.getEmail();
		User usr = service.findUserByEmail(UserEmail);
		if (usr == null) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.setErrorCode(404));
			jsonObject.put("message", res.setDescription("Email does not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		} else {
			Long code = 1000 + usr.getUserId();
			usr.setPasscode(code);
			service.save(usr);
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("track@jespersoft.com");
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("New Passcode");
			passwordResetEmail.setText(
					" Hi " +usr.getName()+", This is your New Passcode for Login: " + usr.getPasscode());
			emailService.sendEmail(passwordResetEmail);
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.SUCCESS);
			jsonObject.put("description", res.setDescription("A link is sent to your mail"));
			return new ResponseEntity(jsonObject, HttpStatus.OK);
		}
	}
}
