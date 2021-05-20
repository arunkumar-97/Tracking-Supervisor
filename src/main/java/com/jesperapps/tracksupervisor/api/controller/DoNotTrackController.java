package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackRequestingEntity;
import com.jesperapps.tracksupervisor.api.message.DoNotTrakResponseEntity;
import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.DoNotTrackService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DoNotTrackController {

	
	@Autowired
	private DoNotTrackService doNotTrackService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ApprovalStatusController approvalStatusService;
	
	
	
	@PostMapping("/track")
	public Response createDoNotTrackfromRequest(@RequestBody DoNotTrackRequestingEntity reqEntity) { 
		
		Response response=new Response();
		DoNotTrack trackFromDb=doNotTrackService.findByUser_userIdAndFromDateAndToDate(reqEntity.getUser().getUserId(),reqEntity.getFromDate(),reqEntity.getToDate());
				if(trackFromDb != null) {
					
					if(trackFromDb.getApprovalstatus().getApprovalStatusId()== 3 ) {
						DoNotTrack doNotTrack=new DoNotTrack(reqEntity);
						doNotTrackService.save(doNotTrack);
						response.setStatusCode(200);
						response.setDescription("Created Successfully");
						return response;
					}else{
						Response response1=new Response(409,"Do Not Track Request Already Applied");
						return response1;
					}
					
				}else {
					DoNotTrack doNotTrack=new DoNotTrack(reqEntity);
					doNotTrackService.save(doNotTrack);
					response.setStatusCode(200);
					response.setDescription("Created Successfully");
					return response;
				}
//				return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/track/{trackId}")
	public ResponseEntity updateUser(@RequestBody DoNotTrackRequestingEntity reqEntity) {
		if (reqEntity.getTrackId() == null) {
			DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity();
			userResponseEntity.setStatusCode(404);
			userResponseEntity.setDescription("DoNotTrackId Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		DoNotTrack trackData=doNotTrackService.findByTrackId(reqEntity.getTrackId());
		if (trackData != null) {
			DoNotTrack newTrack=new DoNotTrack(reqEntity.getTrackId(),trackData.getStatus(),trackData.getFromDate(),trackData.getToDate(),trackData.getCreateDateTime(),trackData.getUpdateDateTime(),reqEntity.getApprovalStatus(),trackData.getUser());
			DoNotTrack dontrackdata=doNotTrackService.save1(newTrack);
			if (dontrackdata != null) {
				DoNotTrackRequestingEntity userReqEntity = new DoNotTrackRequestingEntity(dontrackdata);
				DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity(userReqEntity);
//				UserType uTypes = userRequestEntity.getUserType();
//				for (UserType ut : userResponseEntity.getData().getUserType()) {
//					UserType userType = new UserType(ut, ut);
//					uTypes.add(userType);
//				}
//				userResponseEntity.setUserType(uTypes);
				userResponseEntity.setStatusCode(200);
				userResponseEntity.setDescription("DoNotTrack Request Updated Successfully");
				return new ResponseEntity(userResponseEntity, HttpStatus.OK);
			} else {
				DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity();
				userResponseEntity.setStatusCode(400);
				userResponseEntity.setDescription("Unable to Update DoNotTrack Request");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}
		
		}else {
			DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity();
			userResponseEntity.setStatusCode(404);
			userResponseEntity.setDescription("DoNotTrack Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
	}
		
	
	}
	
	
	
	@GetMapping("/track")
	private List<DoNotTrakResponseEntity> getAllDoNotTrack() {
		
		List<DoNotTrakResponseEntity> response=new ArrayList<>();
		doNotTrackService.findAll().forEach(dontrack ->{
			response.add(new DoNotTrakResponseEntity(dontrack));
			});
			return response;
	}
	
	
	@GetMapping("/track/{trackId}")
	public ResponseEntity viewUser(@PathVariable int trackId)
	{
		DoNotTrack doNotTrackFromDb=doNotTrackService.findByTrackId(trackId);
		DoNotTrakResponseEntity doNotTrackResponse= new DoNotTrakResponseEntity();
		if(doNotTrackFromDb != null)
		{
			
			
			doNotTrackResponse.setTrackId(doNotTrackFromDb.getTrackId());
			doNotTrackResponse.setStatus(doNotTrackFromDb.getStatus());
			doNotTrackResponse.setFromDate(doNotTrackFromDb.getFromDate());
			doNotTrackFromDb.setToDate(doNotTrackFromDb.getToDate());
			doNotTrackFromDb.setCreateDateTime(doNotTrackFromDb.getCreateDateTime());
			doNotTrackFromDb.setUpdateDateTime(doNotTrackFromDb.getUpdateDateTime());
			doNotTrackFromDb.setApprovalstatus(doNotTrackFromDb.getApprovalstatus());
			doNotTrackFromDb.setUser(doNotTrackFromDb.getUser());
			
//			schoolResponse.setSchoolId(schoolFromDb.getSchoolId());
//			schoolResponse.setSchoolName(schoolFromDb.getSchoolName());
//			schoolResponse.setSchoolShortName(schoolFromDb.getSchoolShortName());
//			schoolResponse.setAddress(schoolFromDb.getAddress());
//			schoolResponse.setEmail(schoolFromDb.getEmail());
//			schoolResponse.setMobileNumber(schoolFromDb.getMobileNumber());
//			schoolResponse.setStatus(schoolFromDb.getStatus());
//			schoolResponse.setCity(schoolFromDb.getCity());
//			schoolResponse.setCountry(schoolFromDb.getCountry());
//			schoolResponse.setState(schoolFromDb.getState());
//			schoolResponse.setEducationBoard(schoolFromDb.getEducationBoard());
//			schoolResponse.setMedium(schoolFromDb.getMedium());
//			
//			userResponse.setUserId(user.getUserId());
//			userResponse.setUserName(user.getUserName());
//			userResponse.setEmail(user.getEmail());
//
//			userResponse.setPhoneNumber(user.getPhoneNumber());
//			userResponse.setUserType(user.getUserType().getUserTypeRole());
//			userResponse.setUserProfilePicture(user.getUserProfile().getPictureName());
//			userResponse.setAuthenticationType(user.getAuthentication());
//			userResponse.setStatusCode(200);
//			userResponse.setDescription("User Listed Successfully");
//			userResponse.setSubscriptionFormFromUser(user.getSubscriptionForm());
			

		}
		return new ResponseEntity(doNotTrackResponse,HttpStatus.OK);

	}
	
	
	
	@GetMapping("/track/user/{userId}")
	public ResponseEntity getDoNotTrackByUser(@PathVariable Long userId) {

		List<DoNotTrakResponseEntity> response = new ArrayList<>();
		Optional<User> userFromDb = userService.findById(userId);

		if (userFromDb != null) {
			doNotTrackService.findByUser(userFromDb).forEach(clas -> {
//				if (!clas.getSubscriptionStatus().getStatus().equalsIgnoreCase(SubscriptionStatusTag.SUBSCRIBED)) {
					response.add(new DoNotTrakResponseEntity(clas));
//				} else {
//					return;
//				}
			});

			if (response.isEmpty()) {
				DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity();
				userResponseEntity.setStatusCode(201);
				userResponseEntity.setDescription("No Data is Available");
				return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
			}
		} else {
			DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity();
			userResponseEntity.setStatusCode(201);
			userResponseEntity.setDescription("No Data  Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

		return new ResponseEntity(response, HttpStatus.ACCEPTED);

	}
	
	

	@GetMapping("/track/user/{userId}/{approvalStatusId}")
	public ResponseEntity getDoNotTrackByUser(@PathVariable Long userId,@PathVariable int approvalStatusId) {

		List<DoNotTrakResponseEntity> response = new ArrayList<>();
		Optional<User> userFromDb = userService.findById(userId);
		Optional<ApprovalStatus> approvalStatusFromDb = approvalStatusService.findById(approvalStatusId);

		if (userFromDb != null) {
			doNotTrackService.findByUserAndApprovalStatus(userFromDb,approvalStatusFromDb).forEach(clas -> {
//				if (!clas.getSubscriptionStatus().getStatus().equalsIgnoreCase(SubscriptionStatusTag.SUBSCRIBED)) {
					response.add(new DoNotTrakResponseEntity(clas));
//				} else {
//					return;
//				}
			});

			if (response.isEmpty()) {
				DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity();
				userResponseEntity.setStatusCode(201);
				userResponseEntity.setDescription("No Data is Available");
				return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
			}
		} else {
			DoNotTrakResponseEntity userResponseEntity = new DoNotTrakResponseEntity();
			userResponseEntity.setStatusCode(201);
			userResponseEntity.setDescription("No Data  Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

		return new ResponseEntity(response, HttpStatus.ACCEPTED);

	}

	
}
