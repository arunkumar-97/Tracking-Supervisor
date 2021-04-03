package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//
//import com.jesperapps.schoolmanagement.api.message.UserRequest;
//import com.jesperapps.schoolmanagement.api.message.UserResponse;
import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.message.UserRequestEntity;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.service.UserService;
import com.jesperapps.tracksupervisor.api.service.UserValidationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserValidation {

	@Autowired
	private UserValidationService userValidationService;
	
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/login")
	public ResponseEntity userValidation(@RequestBody UserRequestEntity userRequestEntity) {
		User user = new User(userRequestEntity.getPasscode());
		User userData = userValidationService.findByPasscode(user.getPasscode());
		if(userData == null) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(404);
			userResponseEntity.setMessage("Invalid code");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}else {
			UserRequestEntity userReqEntity = new UserRequestEntity(userData,userData);
			UserResEntity userResEntity = new UserResEntity(userReqEntity);
			Set<UserType> uTypes = new HashSet<UserType>();
			for(UserType ut : userResEntity.getData().getUserType()) {
				UserType userType = new UserType(ut,ut);
				uTypes.add(userType);
			}
			List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
			for(TimeTracking t : userResEntity.getData().getTimeTracking()) {
				TimeTracking timeTracking = new TimeTracking(t);
				timeTrackingList.add(timeTracking);
			}
			if (userResEntity.getData().getOrganization() != null) {
				Organization organization = new Organization(userResEntity.getData().getOrganization(),
						userResEntity.getData().getOrganization().getOrganizationId());
				userResEntity.getData().setOrganization(organization);
			}

			userResEntity.getData().setTimeTracking(timeTrackingList);
			userResEntity.getData().setCreatedByUser(userReqEntity.getCreatedByUser());;
			userResEntity.getData().setUserType(uTypes);
			userResEntity.setStatusCode(200);
			userResEntity.setErrorCode(null);
			userResEntity.setDescription("User Loggedin Successfully");
			return new ResponseEntity(userResEntity, HttpStatus.OK);
		}
	}
	
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/login/email")
	public ResponseEntity loginuser(@RequestBody UserRequestEntity adminRequest) {

		User emailFromDb=userService.findUserByEmailOrPhoneNumber(adminRequest.getEmail(),adminRequest.getPhoneNumber());
		if(emailFromDb!=null) {
		
			if(userService.checkPasswordIsSame(adminRequest.getPassword(), emailFromDb.getPassword())) {
//				if(emailFromDb.getVerificationStatus()==1) {
//					User userFromDb=userService.findByEmailAndPassword(emailFromDb.getEmail(),emailFromDb.getPassword());
//		     		System.out.println(userFromDb);	
//		      		System.out.println("Login Successfull");	
					UserResponseEntity response=new UserResponseEntity();
						
						
 
					response.setStatusCode(200);
					response.setDescription("Login Successfull");
					return  new ResponseEntity(response, HttpStatus.OK);
//				}else {
//					UserResponseEntity response2=new UserResponseEntity();
//					response2.setStatusCode(409);
//					response2.setDescription("Please Verify to login");
//					return new ResponseEntity(response2, HttpStatus.NOT_ACCEPTABLE);
//				}
			
			}else {
				System.out.println("Password Invalid");	
				UserResponseEntity response1=new UserResponseEntity();
				response1.setStatusCode(409);
				response1.setDescription("Password Invalid");
				return new ResponseEntity(response1, HttpStatus.CONFLICT);
				
				
		}

		
		
	}else {
		System.out.println("else email does");	
		UserResponseEntity response3=new UserResponseEntity();
		response3.setStatusCode(409);
		response3.setDescription("Email does not exists");
		 return new ResponseEntity(response3, HttpStatus.CONFLICT);
	}
		
	}
}
