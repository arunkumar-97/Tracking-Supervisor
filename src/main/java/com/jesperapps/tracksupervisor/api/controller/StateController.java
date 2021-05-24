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

import com.jesperapps.tracksupervisor.api.message.CountryResponseEntity;
import com.jesperapps.tracksupervisor.api.message.StateResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.service.CountryService;
import com.jesperapps.tracksupervisor.api.service.StateService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class StateController {

	
	@Autowired
	private StateService stateService;


	@Autowired
	private CountryService countryService;
	
	
	
	@GetMapping("/state")
	private ResponseEntity getAllState() {
			
			List<StateResponseEntity> response=new ArrayList<StateResponseEntity>();
			stateService.findAll().forEach(state ->{
				
					  
					  response.add(new StateResponseEntity(state));
				  
					
				});
				return  new ResponseEntity(response, HttpStatus.OK);
		}

	
	@GetMapping("/state/{countryId}")
	private ResponseEntity getAllStatesBasedOnCountry(@PathVariable Integer countryId) {

		List<StateResponseEntity> response = new ArrayList<>();
		Optional<Country> countryFromDb = countryService.findById(countryId);
		if (countryFromDb != null) {

			stateService.findByCountry(countryFromDb).forEach(country -> {
				
					response.add(new StateResponseEntity(country));
				

			});

			if (response.isEmpty()) {
				StateResponseEntity userResponseEntity = new StateResponseEntity();
				userResponseEntity.setStatusCode(201);
				userResponseEntity.setDescription("No Data is Available");
				return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
			}

		} else {
			StateResponseEntity userResponseEntity = new StateResponseEntity();
			userResponseEntity.setStatusCode(201);
			userResponseEntity.setDescription("No Data  Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}


}
