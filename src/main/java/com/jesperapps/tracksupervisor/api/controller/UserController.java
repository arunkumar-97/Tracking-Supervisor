package com.jesperapps.tracksupervisor.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.internet.MimeMessage;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import com.jesperapps.schoolmanagement.api.service.OtpService;
import com.jesperapps.tracksupervisor.api.entity.AttendanceResEntity;
import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserRequestEntity;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.AttachmentByte;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.service.AttachmentService;
import com.jesperapps.tracksupervisor.api.service.AttendanceService;
import com.jesperapps.tracksupervisor.api.service.EmailService;
import com.jesperapps.tracksupervisor.api.service.OrganizationService;
import com.jesperapps.tracksupervisor.api.service.OtpSmsService;
import com.jesperapps.tracksupervisor.api.service.SecondaryUserService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private EmailService emailservice;
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	private UserService userService;
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private OtpSmsService otpService;
	
	@Autowired
	private SecondaryUserService secondaryUserService;
	

	UserResponseEntity res = new UserResponseEntity();
	@Autowired
	private AttendanceService attendanceService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/user")
	public ResponseEntity createUser(@RequestBody UserRequestEntity userRequestEntity) throws IOException {

		if (userRequestEntity.getUserType().isEmpty() || userRequestEntity.getUserType() == null) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("UserType can't be empty");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		if (userRequestEntity.getPhoneNumber() == null || userRequestEntity.getPhoneNumber().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("PhoneNumber can't be empty");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

		if (userRequestEntity.getAlternatePhoneNumber() == null
				|| userRequestEntity.getAlternatePhoneNumber().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("AlternatePhoneNumber can't be empty");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

		if (userRequestEntity.getName() == null || userRequestEntity.getName().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("Name can't be empty");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

		if (userRequestEntity.getEmail() == null || userRequestEntity.getEmail().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("Email can't be empty");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		if (userRequestEntity.getPassword()== null || userRequestEntity.getPassword().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("PhoneNumber can't be empty");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		if (userRequestEntity.getAttachment() != null) {
			if (userRequestEntity.getAttachment().getFileSize() > 4194304) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(400);
				userResponseEntity.setMessage("Image size exceeded");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}
			if (userRequestEntity.getAttachment().getFileName().isEmpty()
					|| userRequestEntity.getAttachment().getFileName() == null) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(400);
				userResponseEntity.setMessage("FileName can't be empty");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}
			if (userRequestEntity.getAttachment().getFileType().isEmpty()
					|| userRequestEntity.getAttachment().getFileType() == null) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(400);
				userResponseEntity.setMessage("FileType can't be empty");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}
			if (userRequestEntity.getAttachment().getFileSize() == 0
					|| userRequestEntity.getAttachment().getFileSize() == null) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(400);
				userResponseEntity.setMessage("FileSize can't be empty");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}

			if (userRequestEntity.getAttachment().getAttachmentByte() != null) {
				if (userRequestEntity.getAttachment().getAttachmentByte().getFileByte().length == 0
						|| userRequestEntity.getAttachment().getAttachmentByte().getFileByte() == null) {
					UserResEntity userResponseEntity = new UserResEntity();
					userResponseEntity.setErrorCode(400);
					userResponseEntity.setMessage("FileByte can't be empty");
					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
				}

			}

		}
//		Organization organization = new Organization(userRequestEntity.getOrganization());
//		Organization organizationSaved = organizationService.save(organization);
//		if (userRequestEntity.getOrganization().getAttachment() == null) {
//		} else {
//			Attachment att = new Attachment(userRequestEntity.getOrganization().getAttachment(), organizationSaved);
//			organizationSaved.setAttachment(att);
//			att.setOrganization(organizationSaved);
//			attachmentService.save(att);
//		}
//		if (organizationSaved.getAttachment() != null) {
//
//			String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
//					.path(organizationSaved.getAttachment().getAttachmentId().toString()).toUriString();
//
//			String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
//					.path(organizationSaved.getAttachment().getAttachmentId().toString()).toUriString();
//
//			organizationSaved.getAttachment().setFileDownloadUrl(fileDownloadUrl);
//			organizationSaved.getAttachment().setFileViewUrl(fileViewUrl);
//			organizationService.save(organizationSaved);
//		}
		//System.out.println("time tracking"+ userRequestEntity.getTimeTracking().size());
		
		
		
		
		
		
		
		
		
		User user = new User(userRequestEntity, userRequestEntity);
		user.setOrganization(userRequestEntity.getOrganization());
		if (user.getAttachment() == null) {
		} else {
			
			Attachment att = new Attachment(user.getAttachment());
			user.setAttachment(att);
			att.setUser(user);
		}
		List<User> userList = userService.findAllByPhoneNumberOrAlternatePhoneNumber(userRequestEntity.getPhoneNumber(),
				userRequestEntity.getAlternatePhoneNumber());
		if (userList == null) {
//			return postUser(user);
		} else {
			for (User usr : userList) {
				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
					UserResEntity userResponseEntity = new UserResEntity();
					userResponseEntity.setErrorCode(409);
					userResponseEntity.setMessage("PhoneNumber already exists");
					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
				}
			}
//			return postUser(user);
		}

		List<User> userListData = userService.findAllByAlternatePhoneNumberOrPhoneNumber(
				userRequestEntity.getPhoneNumber(), userRequestEntity.getAlternatePhoneNumber());
		if (userListData == null) {
//			return postUser(user);
		} else {
			for (User usr : userListData) {
				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
					UserResEntity userResponseEntity = new UserResEntity();
					userResponseEntity.setErrorCode(409);
					userResponseEntity.setMessage("PhoneNumber already exists");
					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
				}
			}
//			return postUser(user);
		}
		
		List<User> userEmailList = userService.findAllByEmail(
				userRequestEntity.getEmail());
		if(userEmailList == null) {
			
		}else {
			for(User use:userEmailList) {
				if(use.getUserStatus()== null || use.getUserStatus().equals("Active") ||use.getUserStatus().equals("InActive")) {
					UserResEntity entity=new UserResEntity();
					entity.setErrorCode(409);
					entity.setMessage("Email Already Exists");
					return new ResponseEntity(entity, HttpStatus.CONFLICT);
				}
			}
		}
		

		int otp = otpService.generateOTP(userRequestEntity.getPhoneNumber());
		if (otp == 0) {
		} else {
			if (userRequestEntity.getAuthenticationType().equalsIgnoreCase("sms")) {
				sendSms("Your One Time Password(OTP) is " + otp, userRequestEntity.getPhoneNumber());

//			} else if (userRequestEntity.getAuthenticationType().equalsIgnoreCase("Email")) {
//				emailService.sendOTPMail(newUsersList);
//
//			}
		}
		
		}
		
		Set<UserType> userTypes=userRequestEntity.getUserType();
		for(UserType users:userTypes) {
			if(users.getUserTypeId()==1) {
				  System.out.println("Manager");
				List<User> use=userService.findEmployeeByUserTypeAndOrganization(users,userRequestEntity.getOrganization());
				     
				if(use.isEmpty()==false) {
					 System.out.println("has no manager");
					UserResEntity userResponseEntity = new UserResEntity();
					userResponseEntity.setErrorCode(409);
					userResponseEntity.setMessage("Manager already exists in the Organization");
					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT); 
					
				
			}
		}
			
		}
		
		
		return postUser(user);
		
		
	
		}
//		UserResEntity userResponseEntity = new UserResEntity();
//		userResponseEntity.setErrorCode(400);
//		userResponseEntity.setMessage("User Created Successfully");
//		return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}

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

	@SuppressWarnings("unlikely-arg-type")
	private ResponseEntity postUser(User user) {
		//  System.out.println("user"+ user);
		Optional<User> userData = userService.createUser(user);
		Optional<User> createdByUser = userService.findById(user.getCreatedByUser());
		if (userData.isPresent() && createdByUser.isPresent()) {
			Long code = 1000 + userData.get().getUserId();
			userData.get().setPasscode(code);
			        
			Set<UserType> usertypelist =userData.get().getUserType();
			    UserType  userType = usertypelist.stream().findFirst().get();
			    
			
		             if(userType.getUserTypeId() == 1) 
		             {		            	  
		            	 Long createdbyuser = userData.get().getUserId();		            	
		            	 userData.get().setCreatedByUser(createdbyuser);
		            	 
		             }
			

			if (user.getAttachment() != null) {
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();

				String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();
				userData.get().getAttachment().setFileDownloadUrl(fileDownloadUrl);
				userData.get().getAttachment().setFileViewUrl(fileViewUrl);
			}

			User userRes = userService.updatePassCode(userData.get());
			if (userRes == null) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(400);
				userResponseEntity.setMessage("Unable to create User");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			} else {
				
				
		
				try {
					SimpleMailMessage leaveCreatedEmail = new SimpleMailMessage();
					leaveCreatedEmail.setSubject("TrackSupervisor app Login Credentials from Jesperapps");
					leaveCreatedEmail.setText("Login Credentials: \n Your Login Passcode is: " + userRes.getPasscode());
					leaveCreatedEmail.setFrom("track@jespersoft.com");
					leaveCreatedEmail.setTo(userRes.getEmail());
					emailservice.sendEmail(leaveCreatedEmail);
				} catch (Exception ex) {
					System.out.println("ex" + ex);
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(400);
//					userResponseEntity.setMessage("Unable to Send Mail");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
				}
				UserRequestEntity userReqEntity = new UserRequestEntity(userData.get());
				UserResEntity userResponseEntity = new UserResEntity(userReqEntity);
				Set<UserType> ut = new HashSet<UserType>();
				for (UserType ut1 : userResponseEntity.getData().getUserType()) {
					UserType uType = new UserType(ut1);
					ut.add(uType);
				}
				List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
				for (TimeTracking t : userResponseEntity.getData().getTimeTracking()) {
					TimeTracking timeTracking = new TimeTracking(t);
					timeTrackingList.add(timeTracking);
				}
				
				
		for(UserType each:userRes.getUserType()) {
					
					if(each.getUserTypeId()==2 || each.getUserTypeId()==3) {
						
						SecondaryUser secondaryUser=new SecondaryUser(); 
						Set<SecondaryUser> secondaryUserList = createdByUser.get().getSecondaryUser();
						if(secondaryUserList == null) {
							secondaryUserList = new HashSet<>();
						}
						secondaryUserList.add(secondaryUser);
						secondaryUser.setPrimaryUser(createdByUser.get());
						secondaryUser.setSecondaryUser(userRes);
						userService.save(createdByUser.get());
						secondaryUserService.save(secondaryUser);
				
	
					}
					
				}
				userResponseEntity.getData().setTimeTracking(timeTrackingList);
				userResponseEntity.getData().setUserType(ut);
				userResponseEntity.setStatusCode(200);
				userResponseEntity.setErrorCode(null);
				userResponseEntity.setDescription("User Created Successfully.Please check your mail for Login Credentials");
				
				
				
				
				return new ResponseEntity(userResponseEntity, HttpStatus.OK);
			}
		} else {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("Unable to create User");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/user/{userId}")
	public ResponseEntity updateUser(@RequestBody UserRequestEntity userRequestEntity) {
		if (userRequestEntity.getUserId() == null) {
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setErrorCode(404);
			userResponseEntity.setMessage("UserId Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		Optional<User> userDatas = userService.findById(userRequestEntity.getUserId());
		System.out.println("userDatas"+userDatas);
		if (userDatas.isPresent()) {
			User users = new User(userRequestEntity.getUserId(), userRequestEntity);
			users.setCreatedByUser(userDatas.get().getCreatedByUser());
			if (userRequestEntity.getAttachment() == null) {
			} else {
				if (userDatas.get().getAttachment() == null) {
					Attachment attachment = new Attachment(userRequestEntity.getAttachment());
					Attachment att = attachmentService.save(attachment);
					String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/download_image/").path(att.getAttachmentId().toString()).toUriString();

					String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
							.path(att.getAttachmentId().toString()).toUriString();
					att.setFileDownloadUrl(fileDownloadUrl);
					att.setFileViewUrl(fileViewUrl);
					users.setAttachment(att);
				} else {
					Attachment attachment = new Attachment(userRequestEntity.getAttachment());
					users.setAttachment(attachment);
				}
			}
			users.setPasscode(userDatas.get().getPasscode());
			User userData = userService.save(users);
			if (userData != null) {
				UserRequestEntity userReqEntity = new UserRequestEntity(userData);
				UserResEntity userResponseEntity = new UserResEntity(userReqEntity);
				Set<UserType> uTypes = new HashSet<UserType>();
				for (UserType ut : userResponseEntity.getData().getUserType()) {
					UserType userType = new UserType(ut, ut);
					uTypes.add(userType);
				}
				userResponseEntity.getData().setUserType(uTypes);
				userResponseEntity.setStatusCode(200);
				userResponseEntity.setDescription("User Updated Successfully");
				return new ResponseEntity(userResponseEntity, HttpStatus.OK);
			} else {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(400);
				userResponseEntity.setMessage("Unable to Update User");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}
		} else {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(404);
			userResponseEntity.setMessage("User Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/user")
	public ResponseEntity listUser() {
		List<User> userDatas = userService.findAll();
		if (userDatas.isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(204);
			userResponseEntity.setMessage("No Data is Available");
			return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
		} else {
			List<UserResponseEntity> userResponseEntity1 = new ArrayList<UserResponseEntity>();
			for (User user : userDatas) {
				if (user.getStatus() == null || user.getStatus().equals("Active") || user.getStatus().equals("InActive")
						|| user.getStatus().equals("Pending") || user.getStatus().equals("Hold")) {
					UserRequestEntity userRequestEntity = new UserRequestEntity(user, user.getUserId());
					if (userRequestEntity.getAttachment() == null) {

					} else {
						Attachment attachment = new Attachment(userRequestEntity.getAttachment(),
								userRequestEntity.getAttachment());
						if (attachment.getAttachmentByte() != null) {
							AttachmentByte attachmentByte = new AttachmentByte(attachment.getAttachmentByte());
							attachment.setAttachmentByte(attachmentByte);
						}

						userRequestEntity.setAttachment(attachment);
					}
					if (userRequestEntity.getOrganization() != null) {
						Organization organization = new Organization(userRequestEntity.getOrganization(),
								userRequestEntity.getOrganization().getOrganizationId());
						userRequestEntity.setOrganization(organization);
					}

					Set<UserType> userTypes = new HashSet<UserType>();
					for (UserType userType : userRequestEntity.getUserType()) {
						if (userType.getStatus() == null || userType.getStatus().equals("Active")
								|| userType.getStatus().equals("InActive") || userType.getStatus().equals("Pending")
								|| userType.getStatus().equals("Hold")) {
							UserType userType1 = new UserType(userType);
							userTypes.add(userType1);
						}
					}

					userRequestEntity.setUserType(userTypes);
					UserResponseEntity userResponseEntity = new UserResponseEntity(userRequestEntity);
					userResponseEntity1.add(userResponseEntity);
				}
			}
			if (userResponseEntity1.isEmpty()) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(204);
				userResponseEntity.setMessage("No Data is Available");
				return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(userResponseEntity1, HttpStatus.OK);
			}
		}
	}
	
	@GetMapping("/user/organization/{organizationId}")
	public ResponseEntity listUser(@PathVariable("organizationId") Integer organizationId) {
		Organization org = new Organization(organizationId);
		List<User> userDatas = userService.findAllByOrganization(org);
		if (userDatas.isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(204);
			userResponseEntity.setMessage("No Data is Available");
			return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
		} else {
			List<UserResponseEntity> userResponseEntity1 = new ArrayList<UserResponseEntity>();
			for (User user : userDatas) {
				if (user.getStatus() == null || user.getStatus().equals("Active") || user.getStatus().equals("InActive")
						|| user.getStatus().equals("Pending") || user.getStatus().equals("Hold")) {
					UserRequestEntity userRequestEntity = new UserRequestEntity(user, user.getUserId());
					if (userRequestEntity.getAttachment() == null) {

					} else {
						Attachment attachment = new Attachment(userRequestEntity.getAttachment(),
								userRequestEntity.getAttachment());
						if (attachment.getAttachmentByte() != null) {
							AttachmentByte attachmentByte = new AttachmentByte(attachment.getAttachmentByte());
							attachment.setAttachmentByte(attachmentByte);
						}

						userRequestEntity.setAttachment(attachment);
					}
					if (userRequestEntity.getOrganization() != null) {
						Organization organization = new Organization(userRequestEntity.getOrganization(),
								userRequestEntity.getOrganization().getOrganizationId());
						userRequestEntity.setOrganization(organization);
					}

					Set<UserType> userTypes = new HashSet<UserType>();
					for (UserType userType : userRequestEntity.getUserType()) {
						if (userType.getStatus() == null || userType.getStatus().equals("Active")
								|| userType.getStatus().equals("InActive") || userType.getStatus().equals("Pending")
								|| userType.getStatus().equals("Hold")) {
							UserType userType1 = new UserType(userType);
							userTypes.add(userType1);
						}
					}

					userRequestEntity.setUserType(userTypes);
					UserResponseEntity userResponseEntity = new UserResponseEntity(userRequestEntity);
					userResponseEntity1.add(userResponseEntity);
				}
			}
			if (userResponseEntity1.isEmpty()) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(204);
				userResponseEntity.setMessage("No Data is Available");
				return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(userResponseEntity1, HttpStatus.OK);
			}
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/user/{userId}")
	public ResponseEntity getResource(@PathVariable("userId") Long userId) {
		Optional<User> user = userService.findById(userId);
		if (user.isPresent()) {
			if (user.get().getStatus() == null || user.get().getStatus().equals("Active")
					|| user.get().getStatus().equals("InActive") || user.get().getStatus().equals("Pending")
					|| user.get().getStatus().equals("Hold")) {
				UserRequestEntity userRequestEntity = new UserRequestEntity(user, user.get(), user.get().getUserId());
				if (userRequestEntity.getAttachment() == null) {

				} else {
					Attachment attachment = new Attachment(userRequestEntity.getAttachment(),
							userRequestEntity.getAttachment());
					if (attachment.getAttachmentByte() != null) {
						AttachmentByte attachmentByte = new AttachmentByte(attachment.getAttachmentByte());
						attachment.setAttachmentByte(attachmentByte);
					}
					userRequestEntity.setAttachment(attachment);
				}
				if (userRequestEntity.getOrganization() != null) {
					Organization organization = new Organization(userRequestEntity.getOrganization(),
							userRequestEntity.getOrganization().getOrganizationId());
					userRequestEntity.setOrganization(organization);
				}

				Set<UserType> userTypes = new HashSet<UserType>();
				for (UserType userType : userRequestEntity.getUserType()) {
					if (userType.getStatus() == null || userType.getStatus().equals("Active")
							|| userType.getStatus().equals("InActive") || userType.getStatus().equals("Pending")
							|| userType.getStatus().equals("Hold")) {
						UserType userType1 = new UserType(userType);
						userTypes.add(userType1);
					}
				}

				userRequestEntity.setUserType(userTypes);
				return new ResponseEntity(userRequestEntity, HttpStatus.OK);
			}
		} else {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(404);
			userResponseEntity.setMessage("User Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/user/{userId}")
	public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {
		Optional<User> user = userService.findById(userId);
		if (user.isPresent()) {
			User userData = userService.deleteUser(userId);
			if (userData == null) {
				UserResEntity userResponseEntity = new UserResEntity();
				userResponseEntity.setErrorCode(400);
				userResponseEntity.setMessage("Unable to delete User");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			} else {
				UserResEntity userResEntity = new UserResEntity();
				userResEntity.setStatusCode(200);
				userResEntity.setDescription("User Deleted Successfully");
				return new ResponseEntity(userResEntity, HttpStatus.OK);
			}
		} else {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(404);
			userResponseEntity.setMessage("User Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
	}

}
