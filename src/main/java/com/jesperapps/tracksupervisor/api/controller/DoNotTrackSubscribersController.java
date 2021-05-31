package com.jesperapps.tracksupervisor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.configuration.WebSocketConfig;
import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackSubscribersReqEntity;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackSubscribersResEntity;
import com.jesperapps.tracksupervisor.api.message.LiveLocationReqEntity;
import com.jesperapps.tracksupervisor.api.model.DoNotTrackSubscribers;
import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.DoNotTrackSubscribersService;
import com.jesperapps.tracksupervisor.api.service.UserService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/doNotTrack")
public class DoNotTrackSubscribersController {
	
	@Autowired
	private DoNotTrackSubscribersService doNotTrackSubscribersService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ObjectMapper objectMapper;
	
	DoNotTrackSubscribersResEntity res=new DoNotTrackSubscribersResEntity();
	
	@PostMapping("/subscribe")
	public ResponseEntity CreateSubscription(@RequestBody DoNotTrackSubscribersReqEntity doNotTrackSubscribersReqEntity) {
		
//		Response response=new Response();
//		DoNotTrackSubscribers subscriptionFromDb=doNotTrackSubscribersService.findBySubscriptionId(doNotTrackSubscribersReqEntity.getSubscriptionId());
//		User requestUser = doNotTrackSubscribersReqEntity.getUser();
//		if(requestUser != null) {
//			User requestUserFromDb = this.userService.findByUserId(requestUser.getUserId());
//		
//				if(requestUserFromDb != null) {
//					if(subscriptionFromDb == null) {
//						doNotTrackSubscribersReqEntity.setStatus(new Status(1));
//					DoNotTrackSubscribers doNotTrackSubscribers=new DoNotTrackSubscribers(doNotTrackSubscribersReqEntity);
//					doNotTrackSubscribers.setUser(requestUserFromDb);
//					requestUserFromDb.setDoNotTrackSubscribers(doNotTrackSubscribers);
//					doNotTrackSubscribersService.save(doNotTrackSubscribers);
//						response.setStatusCode(200);
//						response.setDescription("User Successfully Subscribed");
//						return new ResponseEntity(response, HttpStatus.ACCEPTED);
//					
//					} else {
//						response.setStatusCode(409);
//						response.setDescription("User already Subscribed");
//						return new ResponseEntity(response, HttpStatus.CONFLICT);
//						
//					}
//				}else {
////					locationDetailsService.save(doNotTrack);
//					response.setStatusCode(409);
//					response.setDescription("No User found");
//					return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//				}
//		} else {
//			response.setStatusCode(409);
//			response.setDescription("User missing on request");
//			return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//		}
		
		User requestUser = doNotTrackSubscribersReqEntity.getUser(),
				userFromDb = requestUser != null && requestUser.getUserId() != null ? this.userService.findByUserId(requestUser.getUserId()) : null;
		ObjectNode jsonObject = objectMapper.createObjectNode();
		jsonObject.put("statusCode", res.SUCCESS);
		jsonObject.put("description", res.setDescription("User Successfully Subscribed"));
		if(userFromDb != null) {
			DoNotTrackSubscribers doNotTrackSubscribers = this.doNotTrackSubscribersService.findByUserId(requestUser);
			if(doNotTrackSubscribers != null) {
				
//				DoNotTrackSubscribers doNotTrackSubscribers1=new DoNotTrackSubscribers(doNotTrackSubscribersReqEntity);
				doNotTrackSubscribers.setSubscriptionId(doNotTrackSubscribersReqEntity.getSubscriptionId());
				doNotTrackSubscribers.setUser(userFromDb);
				requestUser.setDoNotTrackSubscribers(doNotTrackSubscribers);
				doNotTrackSubscribers.setStatus(new Status(1));
				
				jsonObject.put("description", res.setDescription("User Successfully Updated"));
//				liveLocationBasedOnUser.setLatitude(liveLocationRequestEntity.getLatitude());
//				liveLocationBasedOnUser.setLongitude(liveLocationRequestEntity.getLongitude());
			}else {
				doNotTrackSubscribers=new DoNotTrackSubscribers(doNotTrackSubscribersReqEntity);
				userFromDb.setDoNotTrackSubscribers(doNotTrackSubscribers);
				doNotTrackSubscribers.setUser(userFromDb);
				doNotTrackSubscribers.setStatus(new Status(1));
			}
			doNotTrackSubscribersService.save(doNotTrackSubscribers);		
			return new ResponseEntity(jsonObject, HttpStatus.ACCEPTED);
			} else {
				jsonObject.put("statusCode", res.FAILURE);
				jsonObject.put("description", res.setDescription("Unable to Subscribe"));
				return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PostMapping("/unsubscribe")
	public ResponseEntity CreateUnSubscription(@RequestBody DoNotTrackSubscribersReqEntity doNotTrackSubscribersReqEntity) {
		
		Response response=new Response();
		DoNotTrackSubscribers subscriptionFromDb=doNotTrackSubscribersService.findBySubscriptionId(doNotTrackSubscribersReqEntity.getSubscriptionId());
		User requestUser = doNotTrackSubscribersReqEntity.getUser();
		if(requestUser != null) {
			User requestUserFromDb = this.userService.findByUserId(requestUser.getUserId());
		
				if(requestUserFromDb != null) {
					if(subscriptionFromDb != null) {
						subscriptionFromDb.setStatus(new Status(2));
//					DoNotTrackSubscribers doNotTrackSubscribers=new DoNotTrackSubscribers(doNotTrackSubscribersReqEntity);
//					doNotTrackSubscribers.setUser(requestUserFromDb);
//					requestUserFromDb.setDoNotTrackSubscribers(doNotTrackSubscribers);
					doNotTrackSubscribersService.save(subscriptionFromDb);
						response.setStatusCode(200);
						response.setDescription("User Successfully UnSubscribed");
						return new ResponseEntity(response, HttpStatus.ACCEPTED);
					
					} else {
						response.setStatusCode(409);
						response.setDescription("User already UnSubscribed");
						return new ResponseEntity(response, HttpStatus.CONFLICT);
						
					}
				}else {
//					locationDetailsService.save(doNotTrack);
					response.setStatusCode(409);
					response.setDescription("No User found");
					return new ResponseEntity(response, HttpStatus.NOT_FOUND);
				}
		} else {
			response.setStatusCode(409);
			response.setDescription("User missing on request");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);
		}
	}
	

}
