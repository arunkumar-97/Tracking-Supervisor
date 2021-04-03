package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.jesperapps.schoolmanagement.api.model.UserProfilePicture;
//import com.jesperapps.schoolmanagement.api.message.Response;
//import com.jesperapps.schoolmanagement.api.message.SchoolRequest;
//import com.jesperapps.schoolmanagement.api.model.School;
import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizataionWithUserRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationResponseEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationWithUserResponseEntity;
import com.jesperapps.tracksupervisor.api.message.TimeTrackingResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.AttachmentByte;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.AttachmentService;
import com.jesperapps.tracksupervisor.api.service.OrganizationService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ObjectMapper objectMapper;

	private Logger logger = LoggerFactory.getLogger("OrganizationController");
	OrganizationResponseEntity res = new OrganizationResponseEntity();
	
	@PostMapping("/check/organization")
	public ResponseEntity createCheckOrganization(@RequestBody OrganizationRequestEntity organizationRequestEntity) {
		Organization organization = new Organization(organizationRequestEntity,organizationRequestEntity);
		if(organization.getAttachment() == null) {
			
		}else {
			System.out.println("Attachment" + organization.getAttachment());
			Attachment att = organization.getAttachment();
			if(att != null) {
				AttachmentByte attachmentByte=att.getAttachmentByte();
				if(attachmentByte !=null) {
					attachmentByte.setAttachment(att);
//					att.setAttachmentByte(attachmentByte);
				}
				organization.setAttachment(att);
				att.setOrganization(organization);
//				att.setAttachmentByte(att.getAttachmentByte());
			Attachment		attachment=attachmentService.save(att);
			System.out.println("Not Null" + attachment);
			if (attachment != null) {
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
						.path(attachment.getAttachmentId().toString()).toUriString();

				String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
						.path(attachment.getAttachmentId().toString()).toUriString();
				attachment.setFileDownloadUrl(fileDownloadUrl);
				attachment.setFileViewUrl(fileViewUrl);
				attachmentService.save(attachment);
			}
				
			}
//			organization.setAttachment(att);
//			att.setOrganization(organization);
//				attachmentService.save(att);
			
				organizationService.save(organization);
//
//			} else {
//				organizationService.save(organization);
//			}
		}
		return new ResponseEntity(organization, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/organization/user/registration")
	private ResponseEntity createEmployee(@RequestBody OrganizationRequestEntity organizationRequestEntity) {
//		System.out.println(employeeRequest.getEmpoyeeId());
		Organization createdOrganization=organizationService.addOrganization(organizationRequestEntity);
		if(createdOrganization != null) {
			
                     Organization organization = new Organization(createdOrganization.getOrganizationId());
			List<User>	newUser=organizationRequestEntity.getUser();
			System.out.println("user"  + organizationRequestEntity.getUser());
			for(User eachUser:newUser) {
				User user = new User(eachUser,eachUser,organizationRequestEntity);
				user.setOrganization(organization);
				if(organizationRequestEntity.getAttachment() == null) {
					
				}else {
					System.out.println("Attachment" + user.getAttachment());
					Attachment att = user.getAttachment();
					if(att != null) {
						AttachmentByte attachmentByte=att.getAttachmentByte();
						if(attachmentByte !=null) {
							attachmentByte.setAttachment(att);
//							att.setAttachmentByte(attachmentByte);
						}
						organization.setAttachment(att);
//						att.setOrganization(organization);
//						att.setAttachmentByte(att.getAttachmentByte());
					Attachment		attachment=attachmentService.save(att);
					System.out.println("Not Null" + attachment);
					if (attachment != null) {
						String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
								.path(attachment.getAttachmentId().toString()).toUriString();

						String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
								.path(attachment.getAttachmentId().toString()).toUriString();
						attachment.setFileDownloadUrl(fileDownloadUrl);
						attachment.setFileViewUrl(fileViewUrl);
						attachmentService.save(attachment);
					}
						
					}
//					organization.setAttachment(att);
//					att.setOrganization(organization);
//						attachmentService.save(att);
					
					//userService.save(user);
		//
//					} else {
//						organizationService.save(organization);
//					}
				}
				//userService.save(user);

			
			}
			
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new OrganizationResponseEntity(createdOrganization));
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new OrganizationResponseEntity() );
	
		}
		
	}
	
	
//	@PostMapping("/organization/user/registration")
//	public ResponseEntity createSchool(@RequestBody OrganizataionWithUserRequestEntity orgRequest) {
//		
//		
//		Organization organization=organizationService.checkOrganization(orgRequest.getOrganization().getOrganizationName());
//		if(organization != null) {
//			
//			OrganizationWithUserResponseEntity response=new OrganizationWithUserResponseEntity();
//			response.setStatusCode(409);
//			response.setDescription("Organization Already Exists");
//			return  (ResponseEntity) ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//		}else {
//			ResponseEntity	createdOrganization=organizationService.createOrganization(orgRequest);
//			return createdOrganization;
//		}
//	}
//	
	
	
	
	
	@PutMapping("/organization/{organizationId}")
	public ResponseEntity<Organization> updateOrganization(@RequestBody OrganizationRequestEntity organizationRequestEntity) {
		Optional<Organization> Id = organizationService.findById(organizationRequestEntity.getOrganizationId());
		if (Id.isPresent()) {
			Organization organizationReq = new Organization(organizationRequestEntity);
			Organization organization = organizationService.save(organizationReq);
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.SUCCESS);
			jsonObject.put("description", res.setDescription("Organization Updated Successfully"));
			return new ResponseEntity(jsonObject, HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.FAILURE);
			jsonObject.put("message", res.setDescription("Unable to Update Organization"));
			return new ResponseEntity(jsonObject, HttpStatus.CONFLICT);
		}
	}
	
	
	@PostMapping("/organization")
	public ResponseEntity<Organization> createOrganization(@RequestBody OrganizationRequestEntity organizationRequestEntity) {
		
	               //Organization organization = new Organization();
			Organization organizationReq = new Organization(organizationRequestEntity);
			// System.out.println("organizationReq.getAttachment()"+organizationReq.getAttachment());
			if (organizationReq.getAttachment() == null) {
			} else {
				System.out.println("has attachment");
				Attachment att = new Attachment(organizationReq.getAttachment(),organizationReq.getOrganizationId());
				organizationReq.setAttachment(att);
				att.setOrganization(organizationReq);
				
				
			}
			System.out.println("organizationReq"+organizationReq.getAttachment().getFileName());
			Organization organization = organizationService.save(organizationReq);
			if(organization!= null)
			{
				ObjectNode jsonObject = objectMapper.createObjectNode();
				jsonObject.put("statusCode", res.SUCCESS);
				jsonObject.put("description", res.setDescription("Organization Created Successfully"));
				return new ResponseEntity(jsonObject, HttpStatus.OK);
			}else {
				ObjectNode jsonObject = objectMapper.createObjectNode();
				jsonObject.put("statusCode", res.SUCCESS);
				jsonObject.put("description", res.setDescription("Unable to Create Organization"));
				return new ResponseEntity(jsonObject, HttpStatus.CONFLICT);
			}
		
		
	}
	
	@GetMapping("/organization/{organizationId}")
	public ResponseEntity<Optional<OrganizationResponseEntity>> getLeave(@PathVariable("organizationId") Integer id) {
		Optional<Organization> organization = organizationService.findById(id);
		if (organization.isPresent()) {
			OrganizationResponseEntity organizationResponseEntity = new OrganizationResponseEntity(organization.get());
			if (organizationResponseEntity.getAttachment() == null) {

			} else {
				Attachment attachment = new Attachment(organizationResponseEntity.getAttachment(),
						organizationResponseEntity.getAttachment());
				if (attachment.getAttachmentByte() != null) {
					AttachmentByte attachmentByte = new AttachmentByte(attachment.getAttachmentByte());
					attachment.setAttachmentByte(attachmentByte);
				}

				organizationResponseEntity.setAttachment(attachment);
			}
			
			  if(organizationResponseEntity.getStatus()  == null)
			    {
			    	
			    }else {
			    	Status status = new Status(organizationResponseEntity.getStatus());
			    	organizationResponseEntity.setStatus(status); 
			    }
			
			return new ResponseEntity<Optional<OrganizationResponseEntity>>(Optional.of(organizationResponseEntity), HttpStatus.OK);
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.setStatusCode(404));
			jsonObject.put("message", res.setDescription("Organization with id =" + id + " not found"));
			return new ResponseEntity(jsonObject, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/organization")
	private ResponseEntity getAllOrganizations() {
			
			List<OrganizationResponseEntity> response=new ArrayList<OrganizationResponseEntity>();
			organizationService.findAll().forEach(Organization ->{
				  if(Organization.getStatus() ==  null || Organization.getStatus().getStatusId() == 1||Organization.getStatus().getStatusId()==2) {
					  
					  if (Organization.getAttachment() == null) {

						} else {
							Attachment attachment = new Attachment(Organization.getAttachment(),
									Organization.getAttachment());
							if (attachment.getAttachmentByte() != null) {
								AttachmentByte attachmentByte = new AttachmentByte(attachment.getAttachmentByte());
								attachment.setAttachmentByte(attachmentByte);
							}

							Organization.setAttachment(attachment);
						}
					  
					    if(Organization.getStatus()  == null)
					    {
					    	
					    }else {
					    	Status status = new Status(Organization.getStatus());
					    	Organization.setStatus(status); 
					    }
					  response.add(new OrganizationResponseEntity(Organization));
				  }
					
				});
				return  new ResponseEntity(response, HttpStatus.OK);
		}
		

	@DeleteMapping("/organization/{organizationId}")
	public ResponseEntity deactivateOrganization(@PathVariable("organizationId") Integer organizationId) {
		Optional<Organization> organization = organizationService.findById(organizationId);
		if (organization.isPresent()) {
			Organization organizationData = organizationService.deactivateOrganization(organizationId);
			if (organizationData == null) {
				OrganizationResponseEntity organizationResponseEntity = new OrganizationResponseEntity();
				organizationResponseEntity.setErrorCode(400);
				organizationResponseEntity.setMessage("Unable to Deactivate Organization");
				return new ResponseEntity(organizationResponseEntity, HttpStatus.CONFLICT);
			} else {
				OrganizationResponseEntity organizationResEntity = new OrganizationResponseEntity();
				organizationResEntity.setStatusCode(200);
				organizationResEntity.setDescription("Organization Deactivated Successfully");
				return new ResponseEntity(organizationResEntity, HttpStatus.OK);
			}
		} else {
			OrganizationResponseEntity organizationResponseEntity = new OrganizationResponseEntity();
			organizationResponseEntity.setErrorCode(404);
			organizationResponseEntity.setMessage("Organization Not Found");
			return new ResponseEntity(organizationResponseEntity, HttpStatus.CONFLICT);
		}
	}
}
