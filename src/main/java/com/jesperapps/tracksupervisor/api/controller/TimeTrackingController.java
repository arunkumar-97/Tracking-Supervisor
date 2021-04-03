package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.message.TimeTrackingRequestEntity;
import com.jesperapps.tracksupervisor.api.message.TimeTrackingResponseEntity;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.TimeTrackingService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TimeTrackingController {
	
	@Autowired
	private TimeTrackingService timeTrackingService;
	@Autowired
	private ObjectMapper objectMapper;

	private Logger logger = LoggerFactory.getLogger("TimeTrackingController");

	TimeTrackingResponseEntity res = new TimeTrackingResponseEntity();
	
	@PostMapping("/time_tracking")
	public ResponseEntity CreateTimeTracking(@RequestBody TimeTrackingRequestEntity timeTrackingRequestEntity) {
		System.out.println("create" + timeTrackingRequestEntity);
		if (timeTrackingRequestEntity.getStatus() == null) {
			timeTrackingRequestEntity.setStatus("Active");
		}
		if (timeTrackingRequestEntity.getDate() == null) {
			TimeTrackingResponseEntity workPlaceResponseEntity = new TimeTrackingResponseEntity();
			workPlaceResponseEntity.setErrorCode(400);
			workPlaceResponseEntity.setMessage("Date can't be empty");
			return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
		}
		TimeTracking timeTrackingReq = new TimeTracking(timeTrackingRequestEntity);
		TimeTracking timeTrackings = timeTrackingService.save(timeTrackingReq);
		Optional<TimeTracking> leaveId = timeTrackingService.findById(timeTrackings.getTimeTrackingId());
		if (leaveId.isPresent()) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.SUCCESS);
			jsonObject.put("description", res.setDescription("TimeTracking Created Successfully"));
			return new ResponseEntity(jsonObject, HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.FAILURE);
			jsonObject.put("message", res.setDescription("Unable to Create TimeTracking"));
			return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/time_tracking/{id}")
	public ResponseEntity<TimeTracking> updateTimeTracking(
			@RequestBody TimeTrackingRequestEntity timeTrackingRequestEntity) {
		Optional<TimeTracking> Id = timeTrackingService.findById(timeTrackingRequestEntity.getTimeTrackingId());
		if (Id.isPresent()) {
			TimeTracking timeTrackingReq = new TimeTracking(timeTrackingRequestEntity);
			timeTrackingService.save(timeTrackingReq);
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.SUCCESS);
			jsonObject.put("description", res.setDescription("TimeTracking Updated Successfully"));
			return new ResponseEntity(jsonObject, HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.FAILURE);
			jsonObject.put("message", res.setDescription("Unable to Update TimeTracking"));
			return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/time_tracking/{id}")
	public ResponseEntity<TimeTracking> deleteTimeTracking(@PathVariable("id") Long id) {
		Optional<TimeTracking> leave = timeTrackingService.findById(id);
		if (leave.isPresent()) {
			timeTrackingService.deleteTimeTracking(id);
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.SUCCESS);
			jsonObject.put("description", res.setDescription("TimeTracking Deleted Successsfully"));
			return new ResponseEntity(jsonObject, HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.setStatusCode(404));
			jsonObject.put("message", res.setDescription("TimeTracking with id =" + id + " not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/time_tracking")
	public ResponseEntity<List<TimeTrackingResponseEntity>> ListTimeTracking() {
		List<TimeTrackingResponseEntity> resEntity = new ArrayList<TimeTrackingResponseEntity>();
		List<TimeTracking> timeTracking = timeTrackingService.findAll();
		if (timeTracking.isEmpty()) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.setStatusCode(204));
			jsonObject.put("message", res.setDescription("no data"));
			return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
		} else {
			for (TimeTracking t : timeTracking) {
				if (t.getStatus() == null || t.getStatus().equals("Active")
						|| t.getStatus().equals("InActive")
						|| t.getStatus().equals("Pending")) {
					TimeTrackingResponseEntity timeTrackingResponseEntity = new TimeTrackingResponseEntity(t, t);
					User user = new User(timeTrackingResponseEntity.getUser(), timeTrackingResponseEntity.getUser().getUserId());
					timeTrackingResponseEntity.setUser(user);
					resEntity.add(timeTrackingResponseEntity);
				}
				
			}
			if (resEntity.isEmpty()) {
				ObjectNode jsonObject = objectMapper.createObjectNode();
				jsonObject.put("statusCode", res.setStatusCode(204));
				jsonObject.put("message", res.setDescription("no data"));
				return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<TimeTrackingResponseEntity>>(resEntity, HttpStatus.OK);
			}
		}
	}

	@GetMapping("/time_tracking/{id}")
	public ResponseEntity<Optional<TimeTrackingResponseEntity>> getLeave(@PathVariable("id") Long id) {
		Optional<TimeTracking> timeTracking = timeTrackingService.findById(id);
		if (timeTracking.isPresent()) {
			TimeTrackingResponseEntity timeTrackingResponseEntity = new TimeTrackingResponseEntity(timeTracking.get());
			User user = new User(timeTrackingResponseEntity.getUser(), timeTrackingResponseEntity.getUser().getUserId());
			timeTrackingResponseEntity.setUser(user);
			return new ResponseEntity<Optional<TimeTrackingResponseEntity>>(Optional.of(timeTrackingResponseEntity), HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.setStatusCode(404));
			jsonObject.put("message", res.setDescription("TimeTracking with id =" + id + " not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		}
	}


}
