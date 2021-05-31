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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackRequestingEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsRequestEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationResponseEntity;
import com.jesperapps.tracksupervisor.api.message.StateResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.AttachmentByte;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.LocationDetailsService;
import com.jesperapps.tracksupervisor.api.service.OrganizationService;
import com.jesperapps.tracksupervisor.api.service.UserService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LocationDetailsController {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private LocationDetailsService locationDetailsService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private UserService userService;
	
	LocationDetailsResponseEntity res=new LocationDetailsResponseEntity();
	
	@PostMapping("/locationDetails")
	public ResponseEntity createDoNotTrackfromRequest(@RequestBody LocationDetailsRequestEntity reqEntity) { 
		
		Response response=new Response();
		LocationDetails trackFromDb=locationDetailsService.findByOrganizationAndLatitudeAndLongitude(reqEntity.getOrganization(),reqEntity.getLatitude(),reqEntity.getLongitude());
				if(trackFromDb == null) {
					
					LocationDetails doNotTrack=new LocationDetails(reqEntity);
					locationDetailsService.save(doNotTrack);
						response.setStatusCode(200);
						response.setDescription("Location Created Successfully for the Organization");
						return new ResponseEntity(response, HttpStatus.ACCEPTED);
					
					
				}else {
					LocationDetails doNotTrack=new LocationDetails(reqEntity);
//					locationDetailsService.save(doNotTrack);
					response.setStatusCode(409);
					response.setDescription("Location Already Created for the Organization");
					return new ResponseEntity(response, HttpStatus.CONFLICT);
				}
//				return response;
	}
	
	@PostMapping("/locationDetails/user")
	public ResponseEntity createLocationDetailsfromRequest(@RequestBody LocationDetailsRequestEntity reqEntity) { 
		
		Response response=new Response();
		LocationDetails trackFromDb=locationDetailsService.findByUserAndLatitudeAndLongitude(reqEntity.getUser(),reqEntity.getLatitude(),reqEntity.getLongitude());
				if(trackFromDb == null) {
					
					LocationDetails doNotTrack=new LocationDetails(reqEntity);
					locationDetailsService.save(doNotTrack);
						response.setStatusCode(200);
						response.setDescription("Location Created Successfully for the User");
						return new ResponseEntity(response, HttpStatus.ACCEPTED);
					
					
				}else {
					LocationDetails doNotTrack=new LocationDetails(reqEntity);
//					locationDetailsService.save(doNotTrack);
					response.setStatusCode(409);
					response.setDescription("Location Already Created for the User");
					return new ResponseEntity(response, HttpStatus.CONFLICT);
				}
//				return response;
	}
	
	@PutMapping("/locationDetails/{locationId}")
	public ResponseEntity updateOrganization(@RequestBody LocationDetailsRequestEntity organizationRequestEntity) {
		Optional<LocationDetails> Id = locationDetailsService.findById(organizationRequestEntity.getLocationId());
		if (Id.isPresent()) {
			LocationDetails locationDetailsReq = new LocationDetails(organizationRequestEntity,Id);
			LocationDetails organization = locationDetailsService.save(locationDetailsReq);
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.SUCCESS);
			jsonObject.put("description", res.setDescription("Location Updated Successfully"));
			return new ResponseEntity(jsonObject, HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.FAILURE);
			jsonObject.put("message", res.setDescription("Unable to Update Location"));
			return new ResponseEntity(jsonObject, HttpStatus.CONFLICT);
		}
	}
	
	
	@GetMapping("/locationDetails")
	private ResponseEntity getAllState() {
			
			List<LocationDetailsResponseEntity> response=new ArrayList<LocationDetailsResponseEntity>();
			locationDetailsService.findAll().forEach(location ->{
				
					  
					  response.add(new LocationDetailsResponseEntity(location));
				  
					
				});
				return  new ResponseEntity(response, HttpStatus.OK);
		}
	
	@GetMapping("/locationDetails/{organizationId}")
	private ResponseEntity getAllLocationsBasedOnOrganization(@PathVariable Integer organizationId) {

		List<LocationDetailsResponseEntity> response = new ArrayList<>();
		Organization orgFromDb = organizationService.findByOrganizationId(organizationId);
		if (orgFromDb != null) {

			locationDetailsService.findByOrganization(orgFromDb).forEach(organization-> {
				
					response.add(new LocationDetailsResponseEntity(organization));
				

			});

			if (response.isEmpty()) {
				LocationDetailsResponseEntity locationDetailsResponseEntity = new LocationDetailsResponseEntity();
				locationDetailsResponseEntity.setStatusCode(201);
				locationDetailsResponseEntity.setDescription("No Data is Available");
				return new ResponseEntity(locationDetailsResponseEntity, HttpStatus.NOT_FOUND);
			}

		} else {
			LocationDetailsResponseEntity locationDetailsResponseEntity = new LocationDetailsResponseEntity();
			locationDetailsResponseEntity.setStatusCode(201);
			locationDetailsResponseEntity.setDescription("No Data  Not Found");
			return new ResponseEntity(locationDetailsResponseEntity, HttpStatus.CONFLICT);
		}
		return new ResponseEntity(response, HttpStatus.ACCEPTED);
	}
	
//	@GetMapping("/locationDetails/user/{userId}")
//	private ResponseEntity getAllLocationsBasedOnUser(@PathVariable Long userId) {
//
//		List<LocationDetailsResponseEntity> response = new ArrayList<>();
//		User orgFromDb = userService.findByUserId(userId);
//		if (orgFromDb != null) {
//
//			locationDetailsService.findByUser(orgFromDb).forEach(user-> {
//				
//					response.add(new LocationDetailsResponseEntity(user,user));
//				
//
//			});
//
//			if (response.isEmpty()) {
//				LocationDetailsResponseEntity locationDetailsResponseEntity = new LocationDetailsResponseEntity();
//				locationDetailsResponseEntity.setStatusCode(201);
//				locationDetailsResponseEntity.setDescription("No Data is Available");
//				return new ResponseEntity(locationDetailsResponseEntity, HttpStatus.NOT_FOUND);
//			}
//
//		} else {
//			LocationDetailsResponseEntity locationDetailsResponseEntity = new LocationDetailsResponseEntity();
//			locationDetailsResponseEntity.setStatusCode(201);
//			locationDetailsResponseEntity.setDescription("No Data  Not Found");
//			return new ResponseEntity(locationDetailsResponseEntity, HttpStatus.CONFLICT);
//		}
//		return new ResponseEntity(response, HttpStatus.ACCEPTED);
//	}
	
	
//	@GetMapping("/locationDetails/user/{userId}")
//	public ResponseEntity getAllUsersForTheSubscribedClass(@PathVariable Long userId) {
//
//		List<LocationDetailsResponseEntity> response = new ArrayList<>();
//		User userFromDb = userService.findByUserId(userId);
//
//		if (userFromDb != null) {
//			locationDetailsService.findByUser(userFromDb).forEach(clas -> {
//					response.add(new LocationDetailsResponseEntity(clas,clas,userFromDb));
//				
//			});
//
//			if (response.isEmpty()) {
//				LocationDetailsResponseEntity userResponseEntity = new LocationDetailsResponseEntity();
//				userResponseEntity.setStatusCode(201);
//				userResponseEntity.setDescription("No Data is Available");
//				return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
//			}
//		} else {
//			LocationDetailsResponseEntity userResponseEntity = new LocationDetailsResponseEntity();
//			userResponseEntity.setStatusCode(201);
//			userResponseEntity.setDescription("No Data  Not Found");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//
//		return new ResponseEntity(response, HttpStatus.ACCEPTED);
//
//	}
	
	@GetMapping("/locationDetails/user/{userId}")
	public List<LocationDetailsResponseEntity> getClassSubjects(@PathVariable Long userId){
		
		List<LocationDetailsResponseEntity> List= new ArrayList<>();
	
		User requestUser = userService.findByUserId(userId);
		if(requestUser != null) {
			requestUser.getLocationDetails().forEach(location -> {
				LocationDetailsResponseEntity locationDetailsResponseEntity=	new LocationDetailsResponseEntity(location);
				
				User u=new User(locationDetailsResponseEntity.getUser(),locationDetailsResponseEntity.getUser());
				locationDetailsResponseEntity.setUser(u);
				List.add(locationDetailsResponseEntity);
				
			
			});
		
		}
		return (List);
		
	}
	
	
	
	@GetMapping("/locationDetails/location/{locationId}")
	public ResponseEntity<Optional<LocationDetailsResponseEntity>> getLocation(@PathVariable("locationId") Integer locationId) {
		Optional<LocationDetails> locationDetails = locationDetailsService.findById(locationId);
		if (locationDetails.isPresent()) {
			LocationDetailsResponseEntity organizationResponseEntity = new LocationDetailsResponseEntity(locationDetails.get());
			
			
			 
			
			return new ResponseEntity<Optional<LocationDetailsResponseEntity>>(Optional.of(organizationResponseEntity), HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			LocationDetailsResponseEntity res=new LocationDetailsResponseEntity();
			jsonObject.put("statusCode", res.setStatusCode(404));
			jsonObject.put("message", res.setDescription("location with id =" + locationId + " not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		}
	}
}
