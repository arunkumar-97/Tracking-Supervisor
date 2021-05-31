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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.FreeTrialReqEntity;
import com.jesperapps.tracksupervisor.api.message.FreeTrialResEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsRequestEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.service.FreeTrialService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FreeTrialController {
	
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private FreeTrialService freeTrialService;
	
	@PostMapping("/freetrial")
	public ResponseEntity createFreeTrial(@RequestBody FreeTrialReqEntity reqEntity) { 
		
		Response response=new Response();
		FreeTrial freeTrialFromDb=freeTrialService.findByNoOfDays(reqEntity.getNoOfDays());
				if(freeTrialFromDb == null) {
						if(reqEntity.isDefault() == true) {
							List<FreeTrial> freeTrialAll=freeTrialService.findAll();
							for(FreeTrial eachFreeTrial:freeTrialAll) {
								eachFreeTrial.setDefault(false);
								freeTrialService.save(eachFreeTrial);
								
							}
							
						}
						FreeTrial freeTrial=new FreeTrial(reqEntity);
						freeTrialService.save(freeTrial);
							response.setStatusCode(200);
							response.setDescription("FreeTrial Created Successfully for the required No.Of.Days");
							return new ResponseEntity(response, HttpStatus.ACCEPTED);
					
					
				}else {
					FreeTrial doNotTrack=new FreeTrial(reqEntity);
//					locationDetailsService.save(doNotTrack);
					response.setStatusCode(409);
					response.setDescription("FreeTrial Already Created for the required No.Of.Days");
					return new ResponseEntity(response, HttpStatus.CONFLICT);
				}
//				return response;
				
	}
	
	@GetMapping("/freetrial")
	private ResponseEntity getAllFreeTrial() {
			
			List<FreeTrialResEntity> response=new ArrayList<FreeTrialResEntity>();
			freeTrialService.findAll().forEach(freeTrial ->{
				
					  
					  response.add(new FreeTrialResEntity(freeTrial));
				  
					
				});
				return  new ResponseEntity(response, HttpStatus.OK);
		}
	
	@GetMapping("/freetrial/{freetrialId}")
	public ResponseEntity<Optional<FreeTrialResEntity>> getLocation(@PathVariable("freetrialId") Integer freetrialId) {
		Optional<FreeTrial> freeTrial = freeTrialService.findById(freetrialId);
		if (freeTrial.isPresent()) {
			FreeTrialResEntity freeTrialResponseEntity = new FreeTrialResEntity(freeTrial.get());
			
			
			 
			
			return new ResponseEntity<Optional<FreeTrialResEntity>>(Optional.of(freeTrialResponseEntity), HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			LocationDetailsResponseEntity res=new LocationDetailsResponseEntity();
			jsonObject.put("statusCode", res.setStatusCode(404));
			jsonObject.put("message", res.setDescription("location with id =" + freetrialId + " not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		}
	}

}
