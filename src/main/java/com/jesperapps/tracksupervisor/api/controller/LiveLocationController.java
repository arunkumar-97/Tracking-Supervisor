package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.configuration.WebSocketConfig;
import com.jesperapps.tracksupervisor.api.message.LiveLocationReqEntity;
import com.jesperapps.tracksupervisor.api.message.LiveLocationResEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.message.TimeTrackingRequestEntity;
import com.jesperapps.tracksupervisor.api.message.TimeTrackingResponseEntity;
import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.LiveLocationService;
import com.jesperapps.tracksupervisor.api.service.UserService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LiveLocationController {

	@Autowired
	private SimpMessagingTemplate liveLocationMessageController;
	
	
	@Autowired
	private LiveLocationService liveLocationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	LiveLocationResEntity res=new LiveLocationResEntity();
	
	@PostMapping("/livelocation")
	public ResponseEntity CreateLiveLocation(@RequestBody LiveLocationReqEntity liveLocationRequestEntity) {
		
		User liveLocationUpdateUser = liveLocationRequestEntity.getUser();
		ObjectNode jsonObject = objectMapper.createObjectNode();
		jsonObject.put("statusCode", res.SUCCESS);
		jsonObject.put("description", res.setDescription("LiveLocation Created Successfully"));
		if(liveLocationUpdateUser != null && liveLocationUpdateUser.getUserId() != null) {
			LiveLocation liveLocationBasedOnUser = this.liveLocationService.findByUserId(liveLocationUpdateUser);
			if(liveLocationBasedOnUser != null) {
				jsonObject.put("description", res.setDescription("LiveLocation updated successfully"));
				liveLocationBasedOnUser.setLatitude(liveLocationRequestEntity.getLatitude());
				liveLocationBasedOnUser.setLongitude(liveLocationRequestEntity.getLongitude());
			}else {
				liveLocationBasedOnUser = new LiveLocation(liveLocationRequestEntity);	
			}
			liveLocationService.save(liveLocationBasedOnUser);
			this.liveLocationMessageController.convertAndSend(WebSocketConfig.BROKER_BASE_URL+"/"+liveLocationUpdateUser.getUserId(), new LiveLocation(liveLocationRequestEntity));
				return new ResponseEntity(jsonObject, HttpStatus.OK);
			} else {
				jsonObject.put("statusCode", res.FAILURE);
				jsonObject.put("message", res.setDescription("Unable to Create LiveLocation"));
				return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
}
