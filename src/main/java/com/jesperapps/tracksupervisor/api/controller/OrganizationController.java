	package com.jesperapps.tracksupervisor.api.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.HttpsURLConnection;

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
import com.jesperapps.tracksupervisor.api.message.AdminOrgReqEntity;
import com.jesperapps.tracksupervisor.api.message.AdminOrgResEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.AttachmentByte;
import com.jesperapps.tracksupervisor.api.model.ConfirmationToken;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.OrganizationFreeTrial;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.service.AttachmentService;
import com.jesperapps.tracksupervisor.api.service.EmailService;
import com.jesperapps.tracksupervisor.api.service.FreeTrialService;
import com.jesperapps.tracksupervisor.api.service.OrganizationFreeTrialService;
import com.jesperapps.tracksupervisor.api.service.OrganizationService;
import com.jesperapps.tracksupervisor.api.service.OtpSmsService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private OtpSmsService otpService;
	
	@Autowired
	private FreeTrialService freeTrialService;
	
	
	@Autowired
	private OrganizationFreeTrialService organizationFreeTrialService;

	private Logger logger = LoggerFactory.getLogger("OrganizationController");
	OrganizationResponseEntity res = new OrganizationResponseEntity();
	
	
	private final String FROM_ADDRESS = "arun.thril@gmail.com";
//	private final String FROM_ADDRESS = "arun.kumar@jespersoft.com";
	
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
		Organization organizationFromDb	=organizationService.findByOrganizationName(organizationRequestEntity.getOrganizationName());
//		System.out.println(employeeRequest.getEmpoyeeId());
		if(organizationFromDb == null) {
			Organization createdOrganization = null;
			User createduser = null ;
				List<User>	newUser=organizationRequestEntity.getUser();
				
				System.out.println("user"  + organizationRequestEntity.getUser());
				for(User eachUser:newUser) {
					
//				List<User> user1=userService.findAllByPhoneNumberOrEmail(eachUser.getPhoneNumber(),eachUser.getEmail());
//					if(user1== null) {
					
				List<User> emailFromDb=		userService.findAllByEmail(eachUser.getEmail());
				if(emailFromDb.isEmpty()) {
					
					List<User> numberFromDb=userService.findAllByPhoneNumber(eachUser.getPhoneNumber());
					
					if(numberFromDb.isEmpty()){
						
					}else{
						UserResponseEntity userResEntity = new UserResponseEntity();
						userResEntity.setErrorCode(409);
						userResEntity.setMessage("Phone Number Already Exists");
						return new ResponseEntity(userResEntity, HttpStatus.CONFLICT);
					
					}
				}else {
					UserResponseEntity userResEntity = new UserResponseEntity();
					userResEntity.setErrorCode(409);
					userResEntity.setMessage("Email Already Exists");
					return new ResponseEntity(userResEntity, HttpStatus.CONFLICT);
				
					
				}
//				Organization organization;
				
				
				 createdOrganization=organizationService.addOrganization(organizationRequestEntity);
				if(createdOrganization != null) {
					
					Organization organization = new Organization(createdOrganization.getOrganizationId());
					
					
					User user = new User(eachUser,eachUser,organizationRequestEntity);
					Set<UserType> userType=new HashSet<>();
					UserType uType=new UserType();
					uType.setUserTypeId((long) 1);
					userType.add(uType);
					user.setUserType(userType);
					
					int otp = otpService.generateOTP(eachUser.getPhoneNumber());
					if (otp == 0) {
					} else {
						if (user.getAuthenticationType().equalsIgnoreCase("sms")) {
							sendSms("Your One Time Password(OTP) is " + otp, user.getPhoneNumber());

						} else if (user.getAuthenticationType().equalsIgnoreCase("email")) {
							
							
							emailservice.sendOTPMail(user);
			
						}
					}
//					int otp = otpService.generateOTP(user.getPhoneNumber());
//					if (otp == 0) {
//					} else {
//						if (eachUser.getAuthenticationType().equalsIgnoreCase("sms")) {
//							sendSms("Your One Time Password(OTP) is " + otp, user.getPhoneNumber());
	//
////						} else if (userRequestEntity.getAuthenticationType().equalsIgnoreCase("Email")) {
////							emailService.sendOTPMail(newUsersList);
//			//
////						}
//					}
//					
//					}
					System.out.println("Organization " +organization.getOrganizationId());
					user.setOrganization(organization);
					if(organizationRequestEntity.getAttachment() == null) {
						createduser = userService.save(user);
					}else {
						System.out.println("Attachment" + user.getAttachment());
						Attachment att = user.getAttachment();
						if(att != null) {
							AttachmentByte attachmentByte=att.getAttachmentByte();
							if(attachmentByte !=null) {
								System.out.println("Attachment" + attachmentByte.getAttachment());
								attachmentByte.setAttachment(att);
//								att.setAttachmentByte(attachmentByte);
							}
							organization.setAttachment(att);
//							att.setOrganization(organization);
//							att.setAttachmentByte(att.getAttachmentByte());
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
//						organization.setAttachment(att);
//						att.setOrganization(organization);
//							attachmentService.save(att);
						
						//userService.save(user);
			//
//						} else {
//							organizationService.save(organization);
//						}
					}
					}
		                     
						
						
//					}else {
//						UserResponseEntity userResEntity=new UserResponseEntity();
//						userResEntity.setErrorCode(409);
//						userResEntity.setMessage("User Already Exists");
//						return new ResponseEntity(userResEntity, HttpStatus.NOT_FOUND);
//					}
					
				
					//userService.save(user);

				
				}
			
				OrganizationResponseEntity userResEntity = new OrganizationResponseEntity(createdOrganization);
				 List<User> userlist = new ArrayList<User>();
				 userlist.add(createduser);
				 userResEntity.setUser(userlist);
				userResEntity.setErrorCode(200);
				userResEntity.setMessage("Please Check your selected AuthenticationType to verify your OTP ");
				return new ResponseEntity(userResEntity, HttpStatus.OK);
				
				
			}else {
				OrganizationResponseEntity userResEntity = new OrganizationResponseEntity();
				userResEntity.setErrorCode(200);
				userResEntity.setMessage("Organization Already Exists");
				return new ResponseEntity(userResEntity, HttpStatus.OK);
		
			}
			
		}
	
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/organization/superadmin/registration")
	private ResponseEntity createOrganizationBySuperAdmin(@RequestBody AdminOrgReqEntity organizationRequestEntity) {
		Organization organizationFromDb	=organizationService.findByOrganizationName(organizationRequestEntity.getOrganizationName());
//		System.out.println(employeeRequest.getEmpoyeeId());
		if(organizationFromDb == null) {
			Organization createdOrganization = null;
			User createduser = null ;
				User	newUser=organizationRequestEntity.getUser();
				
				System.out.println("user"  + organizationRequestEntity.getUser());
				
				User emailFromDb=		userService.findUserByEmail(newUser.getEmail());
				if(emailFromDb == null) {
					
					User numberFromDb=userService.findByPhoneNumber(newUser.getPhoneNumber());
					
					if(numberFromDb == null){
						
					}else{
						UserResponseEntity userResEntity = new UserResponseEntity();
						userResEntity.setErrorCode(409);
						userResEntity.setMessage("Phone Number Already Exists");
						return new ResponseEntity(userResEntity, HttpStatus.CONFLICT);
					
					}
				}else {
					UserResponseEntity userResEntity = new UserResponseEntity();
					userResEntity.setErrorCode(409);
					userResEntity.setMessage("Email Already Exists");
					return new ResponseEntity(userResEntity, HttpStatus.CONFLICT);
				
					
				}

				
				
				 createdOrganization=organizationService.addOrganization(organizationRequestEntity);
				if(createdOrganization != null) {
					
					Organization organization = new Organization(createdOrganization.getOrganizationId());
					
					
					User user = new User(newUser,newUser,organizationRequestEntity);
					user.setVerificationStatus(1);
					user.setStatus("ACTIVE");
					Set<UserType> userType=new HashSet<>();
					UserType uType=new UserType();
					uType.setUserTypeId((long) 1);
					userType.add(uType);
					user.setUserType(userType);
//					System.out.println("Organization " +organization.getOrganizationId());
					user.setOrganization(organization);
					createduser = userService.save(user);
					
					try{

						
						Properties props = new Properties();

						
						props.put("mail.smtp.auth", "true");
						props.put("mail.smtp.starttls.enable", "true");
//						props.put("mail.smtp.host", "mail.jespersoft.com");
						props.put("mail.smtp.host", "smtp.gmail.com");
						props.put("mail.smtp.port", "587");
//						props.put("mail.smtp.port", "25");


						Authenticator auth = new Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
//								return new PasswordAuthentication(FROM_ADDRESS,"Jesper$2021");
								return new PasswordAuthentication(FROM_ADDRESS,"Arun12345$");
							}
						};
						Session session = Session.getInstance(props, auth);
						Message msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(FROM_ADDRESS, false));
						List<InternetAddress> list = new ArrayList<InternetAddress>();
						
							InternetAddress to1 = new InternetAddress(createduser.getEmail());
							msg.setRecipient(Message.RecipientType.TO, to1);
							list.add(to1);
						
						Address[] addressTo = list.toArray(new Address[] {});
						msg.setRecipients(Message.RecipientType.TO, addressTo);
				
						
						msg.setSubject("PRINTLOK LOGIN CREDENTIALS");
						msg.setText("Hello " + createduser.getName() +".\n "+"Your Login Credentials is "+".\n"+"Email :"+createduser.getEmail() +".\n"
								
								+ "Password :" +createduser.getPassword());
						
						
						msg.setSentDate(new Date());
//						List<InternetAddress> listOfToAddress = new ArrayList<InternetAddress>();
//						for (String cc : emailReqEntity2.getCc()) {
//							InternetAddress cc1 = new InternetAddress(cc);
//							msg.setRecipient(Message.RecipientType.CC, cc1);
//							listOfToAddress.add(cc1);
//						}
//						Address[] address = listOfToAddress.toArray(new Address[] {});
			//
//						msg.setRecipients(Message.RecipientType.CC, address);		
						msg.saveChanges();
						Transport.send(msg);
					}
					catch(Exception e) {
						System.out.println("Exception :" + e.getMessage());
						
					}
					
					
					
					
					
					

					OrganizationFreeTrial orgFreeTrial=new OrganizationFreeTrial();
					orgFreeTrial.setOrganization(createdOrganization);
					orgFreeTrial.setFreeTrial(organizationRequestEntity.getFreeTrial());
					Date date=new Date();
					orgFreeTrial.setStartDate(date);
					if(organizationRequestEntity.getFreeTrial() != null) {
						Optional<FreeTrial> freetrial=freeTrialService.findById(organizationRequestEntity.getFreeTrial().getFreeTrialId());
						Integer noofdays = freetrial.get().getNoOfDays();
						Date endate = new Date();
						    endate.setDate(endate.getDate() + noofdays);
						    orgFreeTrial.setEndDate(endate);
						    
						    
						    
						    System.out.println("StartDate :" + date);
						    System.out.println("StartDate :" + endate);
						    Status status=new Status();
						    status.setStatusId((long) 1);
						    orgFreeTrial.setStatus(status);  
						    organizationFreeTrialService.save(orgFreeTrial);
					}
//					System.out.println("FreeTrial :" + freetrial);
					
					
					    
					}
		                     
						
						
//					}else {
//						UserResponseEntity userResEntity=new UserResponseEntity();
//						userResEntity.setErrorCode(409);
//						userResEntity.setMessage("User Already Exists");
//						return new ResponseEntity(userResEntity, HttpStatus.NOT_FOUND);
//					}
					
				
					//userService.save(user);

				
				
			
				AdminOrgResEntity userResEntity = new AdminOrgResEntity(createdOrganization);
				User userlist = new User(createduser);
//				 userlist.add(createduser);
				 userResEntity.setUser(userlist);
				userResEntity.setErrorCode(200);
				userResEntity.setMessage("Organization Created Successfully");
				return new ResponseEntity(userResEntity, HttpStatus.OK);
				
				
			}else {
				OrganizationResponseEntity userResEntity = new OrganizationResponseEntity();
				userResEntity.setErrorCode(409);
				userResEntity.setMessage("Organization Already Exists");
				return new ResponseEntity(userResEntity, HttpStatus.CONFLICT);
		
			}
			
		}

	
	private void sendSms(String string, String phoneNumber) {
		try {

			String apiKey = "elNIWdPL4TVuhKAGt7BnjMoEw9ZFyYU6cXx5kg2J8zHaiOs01Dn50wUgxpFkDubhRT9Ba87Ny6vlMtWr";
			String sendId = "FSTSMS";
			// important step...
			string = URLEncoder.encode(string, "UTF-8");
			String language = "english";

			String route = "t";

			String myUrl = "https://www.fast2sms.com/dev/bulk?authorization=" + apiKey + "&sender_id=" + sendId
					+ "&message=" + string + "&language=" + language + "&route=" + route + "&numbers=" + phoneNumber;

			// sending get request using java..

			URL url = new URL(myUrl);

			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			System.out.println("Wait..............");

			int code = con.getResponseCode();

			System.out.println("Response code : " + code);

			StringBuffer response = new StringBuffer();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				response.append(line);
			}

			System.out.println(response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	
	
	@PutMapping("/superadmin/organization/{organizationId}")
	public ResponseEntity<Organization> updateOrganizationBySupAdm(@RequestBody AdminOrgReqEntity organizationRequestEntity) {
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
					    
					    List<User> responseuser=new ArrayList<>();
					 
					     for(User user : Organization.getUser())

					    	 {
					    	 
					    	 User user1=new User(user,user);
					    	 responseuser.add(user1);
					    	 
					    	 }
					     
					     Organization.setUser(responseuser);
					     
					     
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
