package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.message.CountryResponseEntity;
import com.jesperapps.tracksupervisor.api.service.CountryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CountryController {

	
	
	@Autowired
	private CountryService countryService;
	
	
	@GetMapping("/country")
	private ResponseEntity getAllCountry() {
			
			List<CountryResponseEntity> response=new ArrayList<CountryResponseEntity>();
			countryService.findAll().forEach(country ->{
				
					  
					  response.add(new CountryResponseEntity(country));
				  
					
				});
				return  new ResponseEntity(response, HttpStatus.OK);
		}
		
}
