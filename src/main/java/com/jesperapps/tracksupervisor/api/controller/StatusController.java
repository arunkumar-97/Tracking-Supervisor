package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.message.StatusResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserTypeResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.service.StatusService;
import com.jesperapps.tracksupervisor.api.service.UserTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StatusController {

	@Autowired
	private StatusService statusService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/status")
	public ResponseEntity listStatus() {
		List<Status> statusDatas = statusService.findAll();
		if (statusDatas.isEmpty()) {
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setErrorCode(204);
			userResponseEntity.setMessage("No Data");
			return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
		}
		List<StatusResponseEntity> statusResponseEntity1 = new ArrayList<StatusResponseEntity>();
		for (Status status : statusDatas) {

			StatusResponseEntity statusResponseEntity = new StatusResponseEntity(status);

			statusResponseEntity1.add(statusResponseEntity);
		}
		return new ResponseEntity(statusResponseEntity1, HttpStatus.OK);
	}
}
