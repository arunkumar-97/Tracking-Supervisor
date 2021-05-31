package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.BackGroundRunReqEntity;
import com.jesperapps.tracksupervisor.api.message.BackGroundRunResEntity;
import com.jesperapps.tracksupervisor.api.message.FreeTrialResEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsRequestEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.BackgroundRun;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.service.BackGroundRunService;

@RestController
public class BackGroundRunController {
	
	@Autowired
	private BackGroundRunService backGroundRunService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@PostMapping("/backgroundrun")
	public ResponseEntity createDoNotTrackfromRequest(@RequestBody BackGroundRunReqEntity reqEntity) { 
		
		Response response=new Response();
//		LocationDetails trackFromDb=locationDetailsService.findByOrganizationAndLatitudeAndLongitude(reqEntity.getOrganization(),reqEntity.getLatitude(),reqEntity.getLongitude());
				
		BackgroundRun backGroundRun=new BackgroundRun(reqEntity);
		backGroundRunService.save(backGroundRun);
						response.setStatusCode(200);
						response.setDescription("Application Running in BackGround");
						return new ResponseEntity(response, HttpStatus.ACCEPTED);
					
				
//				return response;
	}
	
	@GetMapping("/backgroundrun")
	private ResponseEntity getAllbackgroundrun() {
			
			List<BackGroundRunResEntity> response=new ArrayList<BackGroundRunResEntity>();
			backGroundRunService.findAll().forEach(backGround ->{
				
					  
					  response.add(new BackGroundRunResEntity(backGround));
				  
					
				});
				return  new ResponseEntity(response, HttpStatus.OK);
		}

	
	@GetMapping("/backgroundrun/{backgroundrunId}")
	public ResponseEntity<Optional<BackGroundRunResEntity>> getLocation(@PathVariable("backgroundrunId") Integer backgroundrunId) {
		Optional<BackgroundRun> backGroundRun = backGroundRunService.findById(backgroundrunId);
		if (backGroundRun.isPresent()) {
			BackGroundRunResEntity backGroundRunResEntity = new BackGroundRunResEntity(backGroundRun.get());
			
			
			 
			
			return new ResponseEntity<Optional<BackGroundRunResEntity>>(Optional.of(backGroundRunResEntity), HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			BackGroundRunResEntity res=new BackGroundRunResEntity();
			jsonObject.put("statusCode", res.setStatusCode(404));
			jsonObject.put("message", res.setDescription("location with id =" + backgroundrunId + " not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		}
	}
}
