package com.jesperapps.tracksupervisor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.configuration.WebSocketConfig;
import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackSubscribersReqEntity;
import com.jesperapps.tracksupervisor.api.message.LiveLocationReqEntity;
import com.jesperapps.tracksupervisor.api.model.DoNotTrackSubscribers;
import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.DoNotTrackSubscribersService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@RestController
@RequestMapping("/doNotTrack")
public class DoNotTrackSubscribersController {
	
	@Autowired
	private DoNotTrackSubscribersService doNotTrackSubscribersService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/subscribe")
	public ResponseEntity CreateSubscription(@RequestBody DoNotTrackSubscribersReqEntity doNotTrackSubscribersReqEntity) {
		
		Response response=new Response();
		DoNotTrackSubscribers subscriptionFromDb=doNotTrackSubscribersService.findBySubscriptionId(doNotTrackSubscribersReqEntity.getSubscriptionId());
		User requestUser = doNotTrackSubscribersReqEntity.getUser();
		if(requestUser != null) {
			User requestUserFromDb = this.userService.findByUserId(requestUser.getUserId());
		
				if(requestUserFromDb != null) {
					if(subscriptionFromDb == null) {
						doNotTrackSubscribersReqEntity.setStatus(new Status(1));
					DoNotTrackSubscribers doNotTrackSubscribers=new DoNotTrackSubscribers(doNotTrackSubscribersReqEntity);
					doNotTrackSubscribers.setUser(requestUserFromDb);
					requestUserFromDb.setDoNotTrackSubscribers(doNotTrackSubscribers);
					doNotTrackSubscribersService.save(doNotTrackSubscribers);
						response.setStatusCode(200);
						response.setDescription("User Successfully Subscribed");
						return new ResponseEntity(response, HttpStatus.ACCEPTED);
					
					} else {
						response.setStatusCode(409);
						response.setDescription("User already Subscribed");
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
