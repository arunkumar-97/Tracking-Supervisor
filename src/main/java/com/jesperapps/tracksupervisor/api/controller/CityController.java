package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.message.CityResponseEntity;

import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.service.CityService;
import com.jesperapps.tracksupervisor.api.service.StateService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CityController {

	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;
	

	@GetMapping("/city")
	private ResponseEntity getAllState() {
			
			List<CityResponseEntity> response=new ArrayList<CityResponseEntity>();
			cityService.findAll().forEach(city ->{
				
					  
					  response.add(new CityResponseEntity(city));
				  
					
				});
				return  new ResponseEntity(response, HttpStatus.OK);
		}
	
	@GetMapping("/city/{stateId}")
	private ResponseEntity getAllStatesBasedOnCountry(@PathVariable Integer stateId) {

		List<CityResponseEntity> response = new ArrayList<>();
		Optional<State> stateFromDb = stateService.findById(stateId);
		if (stateFromDb != null) {

			cityService.findByState(stateFromDb).forEach(city -> {
				
					response.add(new CityResponseEntity(city));
				

			});

			if (response.isEmpty()) {
				CityResponseEntity userResponseEntity = new CityResponseEntity();
				userResponseEntity.setStatusCode(201);
				userResponseEntity.setDescription("No Data is Available");
				return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
			}

		} else {
			CityResponseEntity userResponseEntity = new CityResponseEntity();
			userResponseEntity.setStatusCode(201);
			userResponseEntity.setDescription("No Data  Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		return new ResponseEntity(response, HttpStatus.ACCEPTED);
	}
	
}
