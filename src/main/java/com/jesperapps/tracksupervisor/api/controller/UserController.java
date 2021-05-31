package com.jesperapps.tracksupervisor.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import com.jesperapps.schoolmanagement.api.service.OtpService;
import com.jesperapps.tracksupervisor.api.entity.AttendanceResEntity;
import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.extra.OtpRequest;
import com.jesperapps.tracksupervisor.api.extra.OtpResponse;
import com.jesperapps.tracksupervisor.api.message.AdminUserReqEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserRequestEntity;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.message.WorkPlaceRequestEntity;
import com.jesperapps.tracksupervisor.api.message.WorkPlaceResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.AttachmentByte;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.OrganizationFreeTrial;
import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.service.AttachmentService;
import com.jesperapps.tracksupervisor.api.service.AttendanceService;
import com.jesperapps.tracksupervisor.api.service.EmailService;
import com.jesperapps.tracksupervisor.api.service.FreeTrialService;
import com.jesperapps.tracksupervisor.api.service.OrganizationFreeTrialService;
import com.jesperapps.tracksupervisor.api.service.OrganizationService;
import com.jesperapps.tracksupervisor.api.service.OtpSmsService;
import com.jesperapps.tracksupervisor.api.service.SecondaryUserService;
import com.jesperapps.tracksupervisor.api.service.UserService;
import com.microsoft.schemas.office.visio.x2012.main.CellType;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController{
	
	private static final String SHEET = "Users";
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
	
	@Autowired
	private FreeTrialService freeTrialService;
	
	
	@Autowired
	private OrganizationFreeTrialService organizationFreeTrialService;
	

	UserResponseEntity res = new UserResponseEntity();
	@Autowired
	private AttendanceService attendanceService;

	
	
	private final String FROM_ADDRESS = "arun.thril@gmail.com";
//	private final String FROM_ADDRESS = "arun.kumar@jespersoft.com";
	
	@PostMapping("/user/uploadfile")
	public ResponseEntity uploadFile(
	    @RequestParam("file") MultipartFile file) {

	  try {
		  InputStream is=file.getInputStream();
	   
		  Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();
	     
	      List<User> users = new ArrayList<User>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();
	        
	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        User user = new User();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	        	
	          Cell currentCell = cellsInRow.next();
//	          System.out.println(currentCell.getRow());
	          switch (cellIdx) {
	         

	          case 0:
	        	  
//	        	    org.apache.poi.ss.usermodel.CellType cel = currentCell.getCellType();
//	        	      System.out.println("cel" +cel);
	        	user.setName(currentCell.getStringCellValue());
	            break;

	          case 1:
//	        	  org.apache.poi.ss.usermodel.CellType cel2 = currentCell.getCellType();
//        	      System.out.println("cel" +cel2);
	        	 user.setEmail(currentCell.getStringCellValue().toString());
//	            tutorial.setDescription(currentCell.getStringCellValue());
	            break;

	          case 2:
//	        	  org.apache.poi.ss.usermodel.CellType cel3 = currentCell.getCellType();
//        	      System.out.println("cel" +cel3);
	        	 String phno=String.valueOf(BigInteger.valueOf((long)currentCell.getNumericCellValue()));
	        	System.out.println("phno :" + phno);
	        	user.setPhoneNumber(phno.replaceAll("\\.", ""));
//	            tutorial.setPublished(currentCell.getBooleanCellValue());
	            break;

	          case 3:
	        	String c1=String.valueOf(currentCell.getNumericCellValue());  
	        	c1=c1.split("\\.")[0];
//	        	System.out.println("c :" + c1);
	        	user.setPostalCode(c1);
	        	break;
	        	
	        	
	        	
	          case 4:
	          
	          user.setAddress(currentCell.getStringCellValue().toString());
	          break;
	          
	          case 5:
		          
		          user.setPassword(currentCell.getStringCellValue());
		          break;
		          
		          
	          case 6:
		          
		          user.setCreatedByUser((long) currentCell.getNumericCellValue());
		          break;   
	          case 7:
	        	  
	        Country c=new Country((long) currentCell.getNumericCellValue());
	        user.setCountry(c);
	          case 8:
	        	  
	  	        State s=new State((long) currentCell.getNumericCellValue());
	  	        user.setStates(s);
	          
	          case 9:
	        	  
		  	        City city=new City((long) currentCell.getNumericCellValue());
		  	        user.setCity(city);
		  	        
	          case 10:
	        	  
		  	        UserType userType=new UserType((long) currentCell.getNumericCellValue());
		  	        Set<UserType> u=new HashSet<UserType>();
		  	      u.add(userType);
		  	      user.setUserType(u);
		  	                        
	  	        
	          default:
	            break;
	          }

	          cellIdx++;
	        }

	        users.add(user);
	      }

	      workbook.close();
	      UserResponseEntity response=new UserResponseEntity();
			List<UserResponseEntity> clas=new ArrayList<>();
			
//		return users;
	for(User each:users) {
			
			User emailFromDb=		userService.findUserByEmail(each.getEmail());
				if(emailFromDb == null) {
					
					User numberFromDb=userService.findByPhoneNumber(each.getPhoneNumber());
				
					if(numberFromDb != null){
					UserResponseEntity res=new UserResponseEntity(each.getName());	
//					System.out.println("RESPONSE" +res.getSchoolEducationBoard());
					   clas.add(res);
//					return new ResponseEntity(userResEntity, HttpStatus.CONFLICT);
				}else {
					User user = new User(each);
					user.setVerificationStatus(1);
					
					User savedUsers = userService.save(user);
					
					SecondaryUser secondaryUser=new SecondaryUser(); 
					//Set<SecondaryUser> secondaryUserList = createdByUser.get().getSecondaryUser();
//					if(secondaryUserList == null) {
//						secondaryUserList = new HashSet<>();
//					}
//					secondaryUserList.add(secondaryUser);
					
					User primaryuser =  new User(savedUsers.getCreatedByUser(),savedUsers.getCreatedByUser());
					secondaryUser.setPrimaryUser(primaryuser);
					secondaryUser.setSecondaryUser(savedUsers);
					
					secondaryUserService.save(secondaryUser);
			
					
					
					
				}
			}else {

				UserResponseEntity res=new UserResponseEntity(each.getName());	
//				System.out.println("RESPONSE" +res.getSchoolEducationBoard());
				   clas.add(res);
				
			}
			
			
			
////			List<User>	userFromDb = userService.findAllByOrganization(each.getOrganization());	
//			if(userFromDb.isEmpty()) {
//				User user = new User(each);
//				User holidays = userService.save(user);
//
//			
//		}else {
////		System.out.println("checking else");
//			UserResponseEntity res=new UserResponseEntity(userFromDb.get(0));	
////				System.out.println("RESPONSE" +res.getSchoolEducationBoard());
//				   clas.add(res);
//			
//		}
	
		}
		if(clas.isEmpty()) {
			response.setStatusCode(200);
			response.setDescription("Successfully Created");
			return new ResponseEntity(response, HttpStatus.OK);
		}else {
			 String descrption = null;
			 for(UserResponseEntity cl :  clas)
			 {     
//				 System.out.println("Before");
				 if(descrption != null) 
				 {
//					 System.out.println("if");
					 descrption = descrption +","+ cl.getName();
				 }else {
					 descrption =   cl.getName();
				 }
				 System.out.println("Description "+ descrption);
			 }
			response.setStatusCode(409);
			response.setDescription(descrption +" " +"user  is Already exists");
			return new ResponseEntity(response, HttpStatus.CONFLICT);
		}
		  
	      
		  
		  
	  }
	  catch (Exception e) {
		  System.out.println("Exception :" + e.getLocalizedMessage());
	    return null;
	  }
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/superadmin/user")
	public ResponseEntity createUserBySuperAdmin(@RequestBody AdminUserReqEntity userRequestEntity) throws IOException {

		
		if (userRequestEntity.getPhoneNumber() == null || userRequestEntity.getPhoneNumber().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("PhoneNumber can't be empty");
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
//		if (userRequestEntity.getAttachment() != null) {
//			if (userRequestEntity.getAttachment().getFileSize() > 4194304) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("Image size exceeded");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileName().isEmpty()
//					|| userRequestEntity.getAttachment().getFileName() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileName can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileType().isEmpty()
//					|| userRequestEntity.getAttachment().getFileType() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileType can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileSize() == 0
//					|| userRequestEntity.getAttachment().getFileSize() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileSize can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//
//			if (userRequestEntity.getAttachment().getAttachmentByte() != null) {
//				if (userRequestEntity.getAttachment().getAttachmentByte().getFileByte().length == 0
//						|| userRequestEntity.getAttachment().getAttachmentByte().getFileByte() == null) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(400);
//					userResponseEntity.setMessage("FileByte can't be empty");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//
//			}
//
//		}
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
		user.setVerificationStatus(1);
		user.setStatus("ACTIVE");
//		System.out.println("userRequestEntity.getOrganization()"+userRequestEntity.getOrganization());
		if(userRequestEntity.getOrganization() != null) {
			user.setOrganization(userRequestEntity.getOrganization());
		}
	
		if (user.getAttachment() == null) {
		} else {
			
			Attachment att = new Attachment(user.getAttachment());
			user.setAttachment(att);
			att.setUser(user);
		}
		if(user.getUserType()== null) {
			
			
			
			Set<UserType> userType=new HashSet<>();
			UserType uType=new UserType();
			uType.setUserTypeId((long) 1);
			userType.add(uType);
			user.setUserType(userType);
		}
		
		
		User emailFromDb=		userService.findUserByEmail(userRequestEntity.getEmail());
		if(emailFromDb == null) {
			
			User numberFromDb=userService.findByPhoneNumber(userRequestEntity.getPhoneNumber());
			
			if(numberFromDb != null){
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
		
		
		
		
//		
//		List<User> userList = userService.findAllByPhoneNumberOrAlternatePhoneNumber(userRequestEntity.getPhoneNumber(),
//				userRequestEntity.getAlternatePhoneNumber());
//		if (userList == null) {
////			return postUser(user);
//		} else {
//			for (User usr : userList) {
//				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(409);
//					userResponseEntity.setMessage("PhoneNumber already exists");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//			}
////			return postUser(user);
//		}
//
//		List<User> userListData = userService.findAllByAlternatePhoneNumberOrPhoneNumber(
//				userRequestEntity.getPhoneNumber(), userRequestEntity.getAlternatePhoneNumber());
//		if (userListData == null) {
////			return postUser(user);
//		} else {
//			for (User usr : userListData) {
//				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(409);
//					userResponseEntity.setMessage("PhoneNumber already exists");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//			}
////			return postUser(user);
//		}
//		
//		List<User> userEmailList = userService.findAllByEmail(
//				userRequestEntity.getEmail());
//		if(userEmailList == null) {
//			
//		}else {
//			for(User use:userEmailList) {
//				if(use.getUserStatus()== null || use.getUserStatus().equals("Active") ||use.getUserStatus().equals("InActive")) {
//					UserResEntity entity=new UserResEntity();
//					entity.setErrorCode(409);
//					entity.setMessage("Email Already Exists");
//					return new ResponseEntity(entity, HttpStatus.CONFLICT);
//				}
//			}
//		}
//		
		
//		int otp = otpService.generateOTP(user.getPhoneNumber());
//		if (otp == 0) {
//		} else {
//			if (user.getAuthenticationType().equalsIgnoreCase("sms")) {
//				sendSms("Your One Time Password(OTP) is " + otp, user.getPhoneNumber());
//
//			} else if (user.getAuthenticationType().equalsIgnoreCase("Email")) {
//				emailservice.sendOTPMail(user);
//
//			}
//		}

//		int otp = otpService.generateOTP(userRequestEntity.getPhoneNumber());
//		if (otp == 0) {
//		} else {
//			if (userRequestEntity.getAuthenticationType().equalsIgnoreCase("sms")) {
//				sendSms("Your One Time Password(OTP) is " + otp, userRequestEntity.getPhoneNumber());
//
////			} else(userRequestEntity.getAuthenticationType().equalsIgnoreCase("Email")) {
////				emailService.sendOTPMail(newUsersList);
////
////			}
//		}
		
		
		
		Set<UserType> userTypes=userRequestEntity.getUserType();
		if(userTypes ==  null) {
			
		}else {
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
		}
	
		
		
		return postUser1(user,userRequestEntity);
		
		
	
		}
	
	
	
	
	private ResponseEntity postUser1(User user,AdminUserReqEntity userRequestEntity) {

		System.out.println("User :" + user);
		Optional<User> userData = userService.createUser(user);
		System.out.println("userData " + userData);
//		Optional<User> createdByUser = userService.findById(user.getCreatedByUser());
//		System.out.println("createdByUser " + createdByUser.get());
		
		try{

			
			Properties props = new Properties();

			
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "mail.jespersoft.com");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
//			props.put("mail.smtp.port", "25");


			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(FROM_ADDRESS,"Jesper$2021");
					return new PasswordAuthentication(FROM_ADDRESS,"Arun12345$");
				}
			};
			Session session = Session.getInstance(props, auth);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM_ADDRESS, false));
			List<InternetAddress> list = new ArrayList<InternetAddress>();
			
				InternetAddress to1 = new InternetAddress(user.getEmail());
				msg.setRecipient(Message.RecipientType.TO, to1);
				list.add(to1);
			
			Address[] addressTo = list.toArray(new Address[] {});
			msg.setRecipients(Message.RecipientType.TO, addressTo);
	
			
			msg.setSubject("PRINTLOK LOGIN CREDENTIALS");
			msg.setText("Hello " + user.getName() +".\n "+"Your Login Credentials is "+".\n"+"Email :"+user.getEmail() +".\n"
					
					+ "Password :" +user.getPassword());
			
			
			msg.setSentDate(new Date());
//			List<InternetAddress> listOfToAddress = new ArrayList<InternetAddress>();
//			for (String cc : emailReqEntity2.getCc()) {
//				InternetAddress cc1 = new InternetAddress(cc);
//				msg.setRecipient(Message.RecipientType.CC, cc1);
//				listOfToAddress.add(cc1);
//			}
//			Address[] address = listOfToAddress.toArray(new Address[] {});
//
//			msg.setRecipients(Message.RecipientType.CC, address);		
			msg.saveChanges();
			Transport.send(msg);
		}
		catch(Exception e) {
			System.out.println("Exception :" + e.getMessage());
			
		}
		
		OrganizationFreeTrial orgFreeTrial=new OrganizationFreeTrial();
		orgFreeTrial.setUser(userData.get());
		orgFreeTrial.setFreeTrial(userRequestEntity.getFreeTrial());
		Date date=new Date();
		orgFreeTrial.setStartDate(date);
		if(userRequestEntity.getFreeTrial() != null) {
			Optional<FreeTrial> freetrial=freeTrialService.findById(userRequestEntity.getFreeTrial().getFreeTrialId());
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
		
		
		
		
		
		
		if (userData.isPresent()) {
			
			
			
			
			
			
			Long code = 1000 + userData.get().getUserId();
			userData.get().setPasscode(code);
			Set<UserType> usertypelist =userData.get().getUserType();
			
			
			if(usertypelist != null) {
				 UserType  userType = usertypelist.stream().findFirst().get();
				    
					
	             if(userType.getUserTypeId() == 1) 
	             {		            	  
	            	
	             }else {
	            	 Long createdbyuser = userData.get().getCreatedByUser();		            	
	            	 userData.get().setCreatedByUser(createdbyuser);
	             }
	             
            	 
		
			}
			   

			if (user.getAttachment() != null) {
//				System.out.println();
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();

				String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();
				userData.get().getAttachment().setFileDownloadUrl(fileDownloadUrl);
				userData.get().getAttachment().setFileViewUrl(fileViewUrl);
			}

		User userRes = userService.updatePassCode(userData.get());
//			if (userRes == null) {
//				System.out.println("Check");
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("Unable to create User");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			} else {
				
				
		
//				try {
//					SimpleMailMessage leaveCreatedEmail = new SimpleMailMessage();
//					leaveCreatedEmail.setSubject("TrackSupervisor app Login Credentials from Jesperapps");
//					leaveCreatedEmail.setText("Login Credentials: \n Your Login Passcode is: " + userRes.getPasscode());
//					leaveCreatedEmail.setFrom("track@jespersoft.com");
//					leaveCreatedEmail.setTo(userRes.getEmail());
//					emailservice.sendEmail(leaveCreatedEmail);
//				} catch (Exception ex) {
//					System.out.println("ex" + ex);
////					UserResEntity userResponseEntity = new UserResEntity();
////					userResponseEntity.setErrorCode(400);
////					userResponseEntity.setMessage("Unable to Send Mail");
////					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
				UserRequestEntity userReqEntity = new UserRequestEntity(userData.get());
				UserResEntity userResponseEntity = new UserResEntity(userReqEntity);
				Set<UserType> ut = new HashSet<UserType>();
				for (UserType ut1 : userResponseEntity.getData().getUserType()) {
					UserType uType = new UserType(ut1);
					ut.add(uType);
				}
				List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
				if(userResponseEntity.getData().getTimeTracking() != null)
				{
					for (TimeTracking t : userResponseEntity.getData().getTimeTracking()) {
						TimeTracking timeTracking = new TimeTracking(t);
						timeTrackingList.add(timeTracking);
					}
				}
				
				
				
		for(UserType each:userRes.getUserType()) {
					
					if((each.getUserTypeId()==2 || each.getUserTypeId()==3) && userRes.getCreatedByUser() != null) {
						
						
						userRes.setVerificationStatus(1);
						
						SecondaryUser secondaryUser=new SecondaryUser(); 
						//Set<SecondaryUser> secondaryUserList = createdByUser.get().getSecondaryUser();
//						if(secondaryUserList == null) {
//							secondaryUserList = new HashSet<>();
//						}
//						secondaryUserList.add(secondaryUser);
						
						User primaryuser =  new User(userRes.getCreatedByUser(),userRes.getCreatedByUser());
						secondaryUser.setPrimaryUser(primaryuser);
						secondaryUser.setSecondaryUser(userRes);
						
						secondaryUserService.save(secondaryUser);
						
				
	
					}
					
				}
				userResponseEntity.getData().setTimeTracking(timeTrackingList);
				userResponseEntity.getData().setUserType(ut);
				userResponseEntity.setStatusCode(200);
				userResponseEntity.setErrorCode(null);
				userResponseEntity.setDescription("User Created Successfully.Please check Your Mail for Login Credentials.");
				
				
				
				
				return new ResponseEntity(userResponseEntity, HttpStatus.OK);
			
		} else {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("Unable to create User");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

	
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/user")
	public ResponseEntity createUser(@RequestBody UserRequestEntity userRequestEntity) throws IOException {

		
		if (userRequestEntity.getPhoneNumber() == null || userRequestEntity.getPhoneNumber().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("PhoneNumber can't be empty");
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
//		if (userRequestEntity.getAttachment() != null) {
//			if (userRequestEntity.getAttachment().getFileSize() > 4194304) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("Image size exceeded");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileName().isEmpty()
//					|| userRequestEntity.getAttachment().getFileName() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileName can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileType().isEmpty()
//					|| userRequestEntity.getAttachment().getFileType() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileType can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileSize() == 0
//					|| userRequestEntity.getAttachment().getFileSize() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileSize can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//
//			if (userRequestEntity.getAttachment().getAttachmentByte() != null) {
//				if (userRequestEntity.getAttachment().getAttachmentByte().getFileByte().length == 0
//						|| userRequestEntity.getAttachment().getAttachmentByte().getFileByte() == null) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(400);
//					userResponseEntity.setMessage("FileByte can't be empty");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//
//			}
//
//		}
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
//		System.out.println("userRequestEntity.getOrganization()"+userRequestEntity.getOrganization());
		if(userRequestEntity.getOrganization() != null) {
			user.setOrganization(userRequestEntity.getOrganization());
		}
	
		if (user.getAttachment() == null) {
		} else {
			
			Attachment att = new Attachment(user.getAttachment());
			user.setAttachment(att);
			att.setUser(user);
		}
		if(user.getUserType()== null) {
			
			
			
			Set<UserType> userType=new HashSet<>();
			UserType uType=new UserType();
			uType.setUserTypeId((long) 1);
			userType.add(uType);
			user.setUserType(userType);
		}
		
		
		User emailFromDb=		userService.findUserByEmail(userRequestEntity.getEmail());
		if(emailFromDb == null) {
			
			User numberFromDb=userService.findByPhoneNumber(userRequestEntity.getPhoneNumber());
			
			if(numberFromDb != null){
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
		
		
		
		
//		
//		List<User> userList = userService.findAllByPhoneNumberOrAlternatePhoneNumber(userRequestEntity.getPhoneNumber(),
//				userRequestEntity.getAlternatePhoneNumber());
//		if (userList == null) {
////			return postUser(user);
//		} else {
//			for (User usr : userList) {
//				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(409);
//					userResponseEntity.setMessage("PhoneNumber already exists");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//			}
////			return postUser(user);
//		}
//
//		List<User> userListData = userService.findAllByAlternatePhoneNumberOrPhoneNumber(
//				userRequestEntity.getPhoneNumber(), userRequestEntity.getAlternatePhoneNumber());
//		if (userListData == null) {
////			return postUser(user);
//		} else {
//			for (User usr : userListData) {
//				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(409);
//					userResponseEntity.setMessage("PhoneNumber already exists");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//			}
////			return postUser(user);
//		}
//		
//		List<User> userEmailList = userService.findAllByEmail(
//				userRequestEntity.getEmail());
//		if(userEmailList == null) {
//			
//		}else {
//			for(User use:userEmailList) {
//				if(use.getUserStatus()== null || use.getUserStatus().equals("Active") ||use.getUserStatus().equals("InActive")) {
//					UserResEntity entity=new UserResEntity();
//					entity.setErrorCode(409);
//					entity.setMessage("Email Already Exists");
//					return new ResponseEntity(entity, HttpStatus.CONFLICT);
//				}
//			}
//		}
//		
		
		int otp = otpService.generateOTP(user.getPhoneNumber());
		if (otp == 0) {
		} else {
			if (user.getAuthenticationType().equalsIgnoreCase("sms")) {
				sendSms("Your One Time Password(OTP) is " + otp, user.getPhoneNumber());

			} else if (user.getAuthenticationType().equalsIgnoreCase("Email")) {
				emailservice.sendOTPMail(user);

			}
		}

//		int otp = otpService.generateOTP(userRequestEntity.getPhoneNumber());
//		if (otp == 0) {
//		} else {
//			if (userRequestEntity.getAuthenticationType().equalsIgnoreCase("sms")) {
//				sendSms("Your One Time Password(OTP) is " + otp, userRequestEntity.getPhoneNumber());
//
////			} else(userRequestEntity.getAuthenticationType().equalsIgnoreCase("Email")) {
////				emailService.sendOTPMail(newUsersList);
////
////			}
//		}
		
		
		
		Set<UserType> userTypes=userRequestEntity.getUserType();
		if(userTypes ==  null) {
			
		}else {
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
		}
	
		
		
		return postUser(user);
		
		
	
		}
//		UserResEntity userResponseEntity = new UserResEntity();
//		userResponseEntity.setErrorCode(400);
//		userResponseEntity.setMessage("User Created Successfully");
//		return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}

	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/createuser")
	public ResponseEntity createUserByPrimaryUser(@RequestBody UserRequestEntity userRequestEntity) throws IOException {

		
		if (userRequestEntity.getPhoneNumber() == null || userRequestEntity.getPhoneNumber().isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("PhoneNumber can't be empty");
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
//		if (userRequestEntity.getAttachment() != null) {
//			if (userRequestEntity.getAttachment().getFileSize() > 4194304) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("Image size exceeded");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileName().isEmpty()
//					|| userRequestEntity.getAttachment().getFileName() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileName can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileType().isEmpty()
//					|| userRequestEntity.getAttachment().getFileType() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileType can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (userRequestEntity.getAttachment().getFileSize() == 0
//					|| userRequestEntity.getAttachment().getFileSize() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileSize can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//
//			if (userRequestEntity.getAttachment().getAttachmentByte() != null) {
//				if (userRequestEntity.getAttachment().getAttachmentByte().getFileByte().length == 0
//						|| userRequestEntity.getAttachment().getAttachmentByte().getFileByte() == null) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(400);
//					userResponseEntity.setMessage("FileByte can't be empty");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//
//			}
//
//		}
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
		
		
		
		
		
		
		
		
		
		User user = new User(userRequestEntity, userRequestEntity,userRequestEntity);
//		System.out.println("userRequestEntity.getOrganization()"+userRequestEntity.getOrganization());
		if(userRequestEntity.getOrganization() != null) {
			user.setOrganization(userRequestEntity.getOrganization());
		}
	
		if (user.getAttachment() == null) {
		} else {
			
			Attachment att = new Attachment(user.getAttachment());
			user.setAttachment(att);
			att.setUser(user);
		}
//		if(user.getUserType()== null) {
//			
//			
//			
//			Set<UserType> userType=new HashSet<>();
//			UserType uType=new UserType();
//			uType.setUserTypeId((long) 1);
//			userType.add(uType);
//			user.setUserType(userType);
//		}
		
		
		User emailFromDb=		userService.findUserByEmail(userRequestEntity.getEmail());
		if(emailFromDb == null) {
			
			User numberFromDb=userService.findByPhoneNumber(userRequestEntity.getPhoneNumber());
			
			if(numberFromDb != null){
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
		
		
		
		
//		
//		List<User> userList = userService.findAllByPhoneNumberOrAlternatePhoneNumber(userRequestEntity.getPhoneNumber(),
//				userRequestEntity.getAlternatePhoneNumber());
//		if (userList == null) {
////			return postUser(user);
//		} else {
//			for (User usr : userList) {
//				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(409);
//					userResponseEntity.setMessage("PhoneNumber already exists");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//			}
////			return postUser(user);
//		}
//
//		List<User> userListData = userService.findAllByAlternatePhoneNumberOrPhoneNumber(
//				userRequestEntity.getPhoneNumber(), userRequestEntity.getAlternatePhoneNumber());
//		if (userListData == null) {
////			return postUser(user);
//		} else {
//			for (User usr : userListData) {
//				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(409);
//					userResponseEntity.setMessage("PhoneNumber already exists");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//			}
////			return postUser(user);
//		}
//		
//		List<User> userEmailList = userService.findAllByEmail(
//				userRequestEntity.getEmail());
//		if(userEmailList == null) {
//			
//		}else {
//			for(User use:userEmailList) {
//				if(use.getUserStatus()== null || use.getUserStatus().equals("Active") ||use.getUserStatus().equals("InActive")) {
//					UserResEntity entity=new UserResEntity();
//					entity.setErrorCode(409);
//					entity.setMessage("Email Already Exists");
//					return new ResponseEntity(entity, HttpStatus.CONFLICT);
//				}
//			}
//		}
//		
		
		int otp = otpService.generateOTP(user.getPhoneNumber());
		if (otp == 0) {
		} else {
			if (user.getAuthenticationType().equalsIgnoreCase("sms")) {
				sendSms("Your One Time Password(OTP) is " + otp, user.getPhoneNumber());

			} else if (user.getAuthenticationType().equalsIgnoreCase("Email")) {
				emailservice.sendOTPMail(user);

			}
		}

//		int otp = otpService.generateOTP(userRequestEntity.getPhoneNumber());
//		if (otp == 0) {
//		} else {
//			if (userRequestEntity.getAuthenticationType().equalsIgnoreCase("sms")) {
//				sendSms("Your One Time Password(OTP) is " + otp, userRequestEntity.getPhoneNumber());
//
////			} else(userRequestEntity.getAuthenticationType().equalsIgnoreCase("Email")) {
////				emailService.sendOTPMail(newUsersList);
////
////			}
//		}
		
		
		
		Set<UserType> userTypes=userRequestEntity.getUserType();
		if(userTypes ==  null) {
			
		}else {
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
		}
	
		
		
		return postUser2(user);
		
		
	
		}
	
	
	private ResponseEntity postUser2(User user) {
		System.out.println("User :" + user);
		Optional<User> userData = userService.createUser(user);
		System.out.println("userData " + userData);
//		Optional<User> createdByUser = userService.findById(user.getCreatedByUser());
//		System.out.println("createdByUser " + createdByUser.get());
		if (userData.isPresent()) {
			
			
			
			
			
			
			Long code = 1000 + userData.get().getUserId();
			userData.get().setPasscode(code);
			Set<UserType> usertypelist =userData.get().getUserType();
			
			
			if(usertypelist != null) {
				 UserType  userType = usertypelist.stream().findFirst().get();
				    
					
	             if(userType.getUserTypeId() == 1) 
	             {		            	  
	            	
	             }else {
	            	 Long createdbyuser = userData.get().getCreatedByUser();		            	
	            	 userData.get().setCreatedByUser(createdbyuser);
	             }
	             
            	 
		
			}
			   

			if (user.getAttachment() != null) {
//				System.out.println();
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();

				String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();
				userData.get().getAttachment().setFileDownloadUrl(fileDownloadUrl);
				userData.get().getAttachment().setFileViewUrl(fileViewUrl);
			}

		User userRes = userService.updatePassCode(userData.get());
//			if (userRes == null) {
//				System.out.println("Check");
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("Unable to create User");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			} else {
				
				
		
//				try {
//					SimpleMailMessage leaveCreatedEmail = new SimpleMailMessage();
//					leaveCreatedEmail.setSubject("TrackSupervisor app Login Credentials from Jesperapps");
//					leaveCreatedEmail.setText("Login Credentials: \n Your Login Passcode is: " + userRes.getPasscode());
//					leaveCreatedEmail.setFrom("track@jespersoft.com");
//					leaveCreatedEmail.setTo(userRes.getEmail());
//					emailservice.sendEmail(leaveCreatedEmail);
//				} catch (Exception ex) {
//					System.out.println("ex" + ex);
////					UserResEntity userResponseEntity = new UserResEntity();
////					userResponseEntity.setErrorCode(400);
////					userResponseEntity.setMessage("Unable to Send Mail");
////					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
				UserRequestEntity userReqEntity = new UserRequestEntity(userData.get());
				UserResEntity userResponseEntity = new UserResEntity(userReqEntity);
				Set<UserType> ut = new HashSet<UserType>();
				for (UserType ut1 : userResponseEntity.getData().getUserType()) {
					UserType uType = new UserType(ut1);
					ut.add(uType);
				}
				List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
				if(userResponseEntity.getData().getTimeTracking() != null)
				{
					for (TimeTracking t : userResponseEntity.getData().getTimeTracking()) {
						TimeTracking timeTracking = new TimeTracking(t);
						timeTrackingList.add(timeTracking);
					}
				}
				
				
				
		for(UserType each:userRes.getUserType()) {
					
					if((each.getUserTypeId()==2 || each.getUserTypeId()==3) && userRes.getCreatedByUser() != null) {
						
						
						userRes.setVerificationStatus(1);
						
						SecondaryUser secondaryUser=new SecondaryUser(); 
						//Set<SecondaryUser> secondaryUserList = createdByUser.get().getSecondaryUser();
//						if(secondaryUserList == null) {
//							secondaryUserList = new HashSet<>();
//						}
//						secondaryUserList.add(secondaryUser);
						
						User primaryuser =  new User(userRes.getCreatedByUser(),userRes.getCreatedByUser());
						secondaryUser.setPrimaryUser(primaryuser);
						secondaryUser.setSecondaryUser(userRes);
						
						secondaryUserService.save(secondaryUser);
				
	
					}
					
				}
				userResponseEntity.getData().setTimeTracking(timeTrackingList);
				userResponseEntity.getData().setUserType(ut);
				userResponseEntity.setStatusCode(200);
				userResponseEntity.setErrorCode(null);
				userResponseEntity.setDescription("User Created Successfully");
				
				
				
				
				return new ResponseEntity(userResponseEntity, HttpStatus.OK);
			
		} else {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("Unable to create User");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
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
			System.out.println("url" + myUrl);
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
		System.out.println("User :" + user);
		Optional<User> userData = userService.createUser(user);
		System.out.println("userData " + userData);
//		Optional<User> createdByUser = userService.findById(user.getCreatedByUser());
//		System.out.println("createdByUser " + createdByUser.get());
		if (userData.isPresent()) {
			
			
			
			
			
			
			Long code = 1000 + userData.get().getUserId();
			userData.get().setPasscode(code);
			Set<UserType> usertypelist =userData.get().getUserType();
			
			
			if(usertypelist != null) {
				 UserType  userType = usertypelist.stream().findFirst().get();
				    
					
	             if(userType.getUserTypeId() == 1) 
	             {		            	  
	            	
	             }else {
	            	 Long createdbyuser = userData.get().getCreatedByUser();		            	
	            	 userData.get().setCreatedByUser(createdbyuser);
	             }
	             
            	 
		
			}
			   

			if (user.getAttachment() != null) {
//				System.out.println();
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();

				String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();
				userData.get().getAttachment().setFileDownloadUrl(fileDownloadUrl);
				userData.get().getAttachment().setFileViewUrl(fileViewUrl);
			}

		User userRes = userService.updatePassCode(userData.get());
//			if (userRes == null) {
//				System.out.println("Check");
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("Unable to create User");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			} else {
				
				
		
//				try {
//					SimpleMailMessage leaveCreatedEmail = new SimpleMailMessage();
//					leaveCreatedEmail.setSubject("TrackSupervisor app Login Credentials from Jesperapps");
//					leaveCreatedEmail.setText("Login Credentials: \n Your Login Passcode is: " + userRes.getPasscode());
//					leaveCreatedEmail.setFrom("track@jespersoft.com");
//					leaveCreatedEmail.setTo(userRes.getEmail());
//					emailservice.sendEmail(leaveCreatedEmail);
//				} catch (Exception ex) {
//					System.out.println("ex" + ex);
////					UserResEntity userResponseEntity = new UserResEntity();
////					userResponseEntity.setErrorCode(400);
////					userResponseEntity.setMessage("Unable to Send Mail");
////					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
				UserRequestEntity userReqEntity = new UserRequestEntity(userData.get());
				UserResEntity userResponseEntity = new UserResEntity(userReqEntity);
				Set<UserType> ut = new HashSet<UserType>();
				for (UserType ut1 : userResponseEntity.getData().getUserType()) {
					UserType uType = new UserType(ut1);
					ut.add(uType);
				}
				List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
				if(userResponseEntity.getData().getTimeTracking() != null)
				{
					for (TimeTracking t : userResponseEntity.getData().getTimeTracking()) {
						TimeTracking timeTracking = new TimeTracking(t);
						timeTrackingList.add(timeTracking);
					}
				}
				
				
				
		for(UserType each:userRes.getUserType()) {
					
					if((each.getUserTypeId()==2 || each.getUserTypeId()==3) && userRes.getCreatedByUser() != null) {
						
						
						userRes.setVerificationStatus(1);
						
						SecondaryUser secondaryUser=new SecondaryUser(); 
						//Set<SecondaryUser> secondaryUserList = createdByUser.get().getSecondaryUser();
//						if(secondaryUserList == null) {
//							secondaryUserList = new HashSet<>();
//						}
//						secondaryUserList.add(secondaryUser);
						
						User primaryuser =  new User(userRes.getCreatedByUser(),userRes.getCreatedByUser());
						secondaryUser.setPrimaryUser(primaryuser);
						secondaryUser.setSecondaryUser(userRes);
						
						secondaryUserService.save(secondaryUser);
				
	
					}
					
				}
				userResponseEntity.getData().setTimeTracking(timeTrackingList);
				userResponseEntity.getData().setUserType(ut);
				userResponseEntity.setStatusCode(200);
				userResponseEntity.setErrorCode(null);
				userResponseEntity.setDescription("User Created Successfully.Please check your Selected AuthenticationType to verify your OTP");
				
				
				
				
				return new ResponseEntity(userResponseEntity, HttpStatus.OK);
			
		} else {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(400);
			userResponseEntity.setMessage("Unable to create User");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}

	}

	
	@PostMapping("/check-otp")
	public ResponseEntity checkOTP(@RequestBody List<OtpRequest> emailOtpRequest){
		
		List<OtpResponse> responseList = userService.validateOTP(emailOtpRequest);
		for(OtpResponse each:responseList) {
			if(each.getStatusCode()==200) {
				return new ResponseEntity(each,HttpStatus.ACCEPTED);
			}else if(each.getStatusCode()==400) {
				return new ResponseEntity(each,HttpStatus.CONFLICT);
			}
		}
		OtpResponse response=new OtpResponse();
		response.setStatusCode(409);
		response.setDescription("Email Id Does Not Exists");
		return new ResponseEntity(response,HttpStatus.CONFLICT);
	}
	
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@PostMapping("/multiple/user")
//	public ResponseEntity createUser(@RequestBody List<UserRequestEntity> userRequestEntity) throws IOException {
//
//		List<UserResponseEntity> response=new ArrayList<>();
//		for(UserRequestEntity each:userRequestEntity) {
//		
//		if (each.getUserType().isEmpty() || each.getUserType() == null) {
//			UserResEntity userResponseEntity = new UserResEntity();
//			userResponseEntity.setErrorCode(400);
//			userResponseEntity.setMessage("UserType can't be empty");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//		if (each.getPhoneNumber() == null || each.getPhoneNumber().isEmpty()) {
//			UserResEntity userResponseEntity = new UserResEntity();
//			userResponseEntity.setErrorCode(400);
//			userResponseEntity.setMessage("PhoneNumber can't be empty");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//
//		if (each.getAlternatePhoneNumber() == null
//				|| each.getAlternatePhoneNumber().isEmpty()) {
//			UserResEntity userResponseEntity = new UserResEntity();
//			userResponseEntity.setErrorCode(400);
//			userResponseEntity.setMessage("AlternatePhoneNumber can't be empty");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//
//		if (each.getName() == null || each.getName().isEmpty()) {
//			UserResEntity userResponseEntity = new UserResEntity();
//			userResponseEntity.setErrorCode(400);
//			userResponseEntity.setMessage("Name can't be empty");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//
//		if (each.getEmail() == null || each.getEmail().isEmpty()) {
//			UserResEntity userResponseEntity = new UserResEntity();
//			userResponseEntity.setErrorCode(400);
//			userResponseEntity.setMessage("Email can't be empty");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//		if (each.getPassword()== null || each.getPassword().isEmpty()) {
//			UserResEntity userResponseEntity = new UserResEntity();
//			userResponseEntity.setErrorCode(400);
//			userResponseEntity.setMessage("PhoneNumber can't be empty");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//		if (each.getAttachment() != null) {
//			if (each.getAttachment().getFileSize() > 4194304) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("Image size exceeded");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (each.getAttachment().getFileName().isEmpty()
//					|| each.getAttachment().getFileName() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileName can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (each.getAttachment().getFileType().isEmpty()
//					|| each.getAttachment().getFileType() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileType can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//			if (each.getAttachment().getFileSize() == 0
//					|| each.getAttachment().getFileSize() == null) {
//				UserResEntity userResponseEntity = new UserResEntity();
//				userResponseEntity.setErrorCode(400);
//				userResponseEntity.setMessage("FileSize can't be empty");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//
//			if (each.getAttachment().getAttachmentByte() != null) {
//				if (each.getAttachment().getAttachmentByte().getFileByte().length == 0
//						|| each.getAttachment().getAttachmentByte().getFileByte() == null) {
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(400);
//					userResponseEntity.setMessage("FileByte can't be empty");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//
//			}
//
//		}
////		Organization organization = new Organization(userRequestEntity.getOrganization());
////		Organization organizationSaved = organizationService.save(organization);
////		if (userRequestEntity.getOrganization().getAttachment() == null) {
////		} else {
////			Attachment att = new Attachment(userRequestEntity.getOrganization().getAttachment(), organizationSaved);
////			organizationSaved.setAttachment(att);
////			att.setOrganization(organizationSaved);
////			attachmentService.save(att);
////		}
////		if (organizationSaved.getAttachment() != null) {
////
////			String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
////					.path(organizationSaved.getAttachment().getAttachmentId().toString()).toUriString();
////
////			String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
////					.path(organizationSaved.getAttachment().getAttachmentId().toString()).toUriString();
////
////			organizationSaved.getAttachment().setFileDownloadUrl(fileDownloadUrl);
////			organizationSaved.getAttachment().setFileViewUrl(fileViewUrl);
////			organizationService.save(organizationSaved);
////		}
//		//System.out.println("time tracking"+ userRequestEntity.getTimeTracking().size());
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		User user = new User(each, each);
//		System.out.println("userRequestEntity.getOrganization()"+each.getOrganization());
//		if(each.getOrganization() != null) {
//			user.setOrganization(each.getOrganization());
//		}
//	
//		if (user.getAttachment() == null) {
//		} else {
//			
//			Attachment att = new Attachment(user.getAttachment());
//			user.setAttachment(att);
//			att.setUser(user);
//		}
//		List<User> userList = userService.findAllByPhoneNumberOrAlternatePhoneNumber(each.getPhoneNumber(),
//				each.getAlternatePhoneNumber());
//		if (userList.size()==0) {
////			return postUser(user);
//		} else {
//			
//			
//			UserResponseEntity res = new UserResponseEntity(each);
//			System.out.println("RESPONSE" + res.getPhoneNumber());
//			res.setDescription(res.getPhoneNumber() + " Phone exists");
//			response.add(res);
//			
//			
//			
//			
////			for (User usr : userList) {
////				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
////					UserResEntity userResponseEntity = new UserResEntity();
////					userResponseEntity.setErrorCode(409);
////					userResponseEntity.setMessage("PhoneNumber already exists");
////					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
////				}
////			}
////			return postUser(user);
//		}
//
//		List<User> userListData = userService.findAllByAlternatePhoneNumberOrPhoneNumber(
//				each.getPhoneNumber(), each.getAlternatePhoneNumber());
//		if (userListData.size()==0) {
////			return postUser(user);
//		} else {
//			UserResponseEntity res = new UserResponseEntity(each);
//			System.out.println("RESPONSE" + res.getPhoneNumber());
//			res.setDescription(res.getPhoneNumber() + " Phone exists");
//			response.add(res);
////			return postUser(user);
//		}
//		
//		List<User> userEmailList = userService.findAllByEmail(
//				each.getEmail());
//		if(userEmailList.size()==0) {
//			
//		}else {
//			String description="";
//			UserResponseEntity res = new UserResponseEntity(each);
//			System.out.println("RESPONSE" + res.getEmail());
//			description=res.getEmail() + " Email Exists";
////			List<User> userList1 = userService.findAllByPhoneNumberOrAlternatePhoneNumber(each.getPhoneNumber(),
////					each.getAlternatePhoneNumber());
////			if(userList1 != null) {
////				description += "," + user.getPhoneNumber() + " Phone Exists " ;
////			}
//			res.setDescription(description);
////			res.setDescription(res.getEmail() + " Email Exists");
//			response.add(res);
//		}
//		
//		
//		int otp = otpService.generateOTP(user.getPhoneNumber());
//		if (otp == 0) {
//		} else {
//			if (user.getAuthenticationType().equalsIgnoreCase("sms")) {
//				sendSms("Your One Time Password(OTP) is " + otp, user.getPhoneNumber());
//
//			} else if (user.getAuthenticationType().equalsIgnoreCase("Email")) {
//				emailservice.sendOTPMail(user);
//
//			}
//		}
//
////		int otp = otpService.generateOTP(userRequestEntity.getPhoneNumber());
////		if (otp == 0) {
////		} else {
////			if (userRequestEntity.getAuthenticationType().equalsIgnoreCase("sms")) {
////				sendSms("Your One Time Password(OTP) is " + otp, userRequestEntity.getPhoneNumber());
////
//////			} else(userRequestEntity.getAuthenticationType().equalsIgnoreCase("Email")) {
//////				emailService.sendOTPMail(newUsersList);
//////
//////			}
////		}
//		
//		
//		
//		Set<UserType> userTypes=each.getUserType();
//		for(UserType users:userTypes) {
//			if(users.getUserTypeId()==1) {
//				  System.out.println("Manager");
//				List<User> use=userService.findEmployeeByUserTypeAndOrganization(users,each.getOrganization());
//				     
//				if(use.isEmpty()==false) {
//					 System.out.println("has no manager");
//					UserResEntity userResponseEntity = new UserResEntity();
//					userResponseEntity.setErrorCode(409);
//					userResponseEntity.setMessage("Manager already exists in the Organization");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT); 
//					
//				
//			}
//		}
//			
//		}
//		
//		
//		System.out.println("User :" + user);
//		Optional<User> userData = userService.createUser(user);
//		System.out.println("userData " + userData);
////		Optional<User> createdByUser = userService.findById(user.getCreatedByUser());
////		System.out.println("createdByUser " + createdByUser.get());
//		if (userData.isPresent()) {
//			Long code = 1000 + userData.get().getUserId();
//			userData.get().setPasscode(code);
//			Set<UserType> usertypelist =userData.get().getUserType();
//			    UserType  userType = usertypelist.stream().findFirst().get();
//			    
//			
//		             if(userType.getUserTypeId() == 1) 
//		             {		            	  
//		            	 Long createdbyuser = userData.get().getUserId();		            	
//		            	 userData.get().setCreatedByUser(createdbyuser);
//		            	 
//		             }
//			
//
//			if (user.getAttachment() != null) {
//				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
//						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();
//
//				String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
//						.path(userData.get().getAttachment().getAttachmentId().toString()).toUriString();
//				userData.get().getAttachment().setFileDownloadUrl(fileDownloadUrl);
//				userData.get().getAttachment().setFileViewUrl(fileViewUrl);
//			}
//
//		User userRes = userService.updatePassCode(userData.get());
////			if (userRes == null) {
////				System.out.println("Check");
////				UserResEntity userResponseEntity = new UserResEntity();
////				userResponseEntity.setErrorCode(400);
////				userResponseEntity.setMessage("Unable to create User");
////				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
////			} else {
//				
//				
//		
////				try {
////					SimpleMailMessage leaveCreatedEmail = new SimpleMailMessage();
////					leaveCreatedEmail.setSubject("TrackSupervisor app Login Credentials from Jesperapps");
////					leaveCreatedEmail.setText("Login Credentials: \n Your Login Passcode is: " + userRes.getPasscode());
////					leaveCreatedEmail.setFrom("track@jespersoft.com");
////					leaveCreatedEmail.setTo(userRes.getEmail());
////					emailservice.sendEmail(leaveCreatedEmail);
////				} catch (Exception ex) {
////					System.out.println("ex" + ex);
//////					UserResEntity userResponseEntity = new UserResEntity();
//////					userResponseEntity.setErrorCode(400);
//////					userResponseEntity.setMessage("Unable to Send Mail");
//////					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
////				}
//				UserRequestEntity userReqEntity = new UserRequestEntity(userData.get());
//				UserResEntity userResponseEntity = new UserResEntity(userReqEntity);
//				Set<UserType> ut = new HashSet<UserType>();
//				for (UserType ut1 : userResponseEntity.getData().getUserType()) {
//					UserType uType = new UserType(ut1);
//					ut.add(uType);
//				}
//				List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
//				if(userResponseEntity.getData().getTimeTracking() != null)
//				{
//					for (TimeTracking t : userResponseEntity.getData().getTimeTracking()) {
//						TimeTracking timeTracking = new TimeTracking(t);
//						timeTrackingList.add(timeTracking);
//					}
//				}
//				
//				
//				
//		for(UserType each1:userRes.getUserType()) {
//					
//					if((each1.getUserTypeId()==2 || each1.getUserTypeId()==3) && userRes.getCreatedByUser() != null) {
//						
//						SecondaryUser secondaryUser=new SecondaryUser(); 
//						//Set<SecondaryUser> secondaryUserList = createdByUser.get().getSecondaryUser();
////						if(secondaryUserList == null) {
////							secondaryUserList = new HashSet<>();
////						}
////						secondaryUserList.add(secondaryUser);
//						
//						User primaryuser =  new User(userRes.getCreatedByUser(),userRes.getCreatedByUser());
//						secondaryUser.setPrimaryUser(primaryuser);
//						secondaryUser.setSecondaryUser(userRes);
//						
//						secondaryUserService.save(secondaryUser);
//				
//	
//					}
//					
//				}
//				userResponseEntity.getData().setTimeTracking(timeTrackingList);
//				userResponseEntity.getData().setUserType(ut);
//				userResponseEntity.setStatusCode(200);
//				userResponseEntity.setErrorCode(null);
//				userResponseEntity.setDescription("User Created Successfully.Please check your mail for Login Credentials");
//				
//				
//				
//				
//				
//			
//		} 
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	
//		}
//		
//		
//		
//		
//		if(response.isEmpty()) {
//			UserResEntity userResponseEntity = new UserResEntity();
//			userResponseEntity.setErrorCode(400);
//			userResponseEntity.setMessage("Successfully Created");
//			return new ResponseEntity(userResponseEntity, HttpStatus.OK);
//		}else {
//			String descrption = null;
//			 for(UserResponseEntity res :  response)
//			 {     
//				 if(descrption != null) 
//				 {
//					 descrption = descrption +","+ res.getName();
//				 }else {
//					 descrption =  res.getName();
//				 }
//				 
//			 }
//			 UserResEntity userResponseEntity = new UserResEntity();
//			 userResponseEntity.setStatusCode(409);
//			 userResponseEntity.setDescription(descrption +" " +"User  is Already Created for the Organization");
//			 return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//		
//}
	
	
	
	
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
			System.out.println("UserType :" + users.getUserType());
			
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
			System.out.println("Users :" + users.getUserId());
			User userData = userService.save(users);
			
			
			if (userData != null) {
				UserRequestEntity userReqEntity = new UserRequestEntity(userData);
				UserResEntity userResponseEntity = new UserResEntity(userReqEntity);
				Set<UserType> uTypes = new HashSet<UserType>();
				if(userResponseEntity.getData().getUserType()!= null) {
					for (UserType ut : userResponseEntity.getData().getUserType()) {
						UserType userType = new UserType(ut, ut);
						uTypes.add(userType);
					}
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

	
	@PostMapping("/multiple/user")
	public ResponseEntity createWorkPlace(@RequestBody List<UserRequestEntity> userRequestEntity) {
		UserResponseEntity response=new UserResponseEntity();
		List<UserResponseEntity> clas=new ArrayList<>();
		for(UserRequestEntity each:userRequestEntity) {
			
			User emailFromDb=		userService.findUserByEmail(each.getEmail());
			System.out.println("email "+each.getEmail());
			System.out.println("no"+each.getPhoneNumber());

				if(emailFromDb == null) {
					
					User numberFromDb=userService.findByPhoneNumber(each.getPhoneNumber());
				
					if(numberFromDb != null){
						System.out.println("no"+each.getPhoneNumber());
					UserResponseEntity res=new UserResponseEntity(each.getPhoneNumber());	
//					System.out.println("RESPONSE" +res.getSchoolEducationBoard());
					   clas.add(res);
//					return new ResponseEntity(userResEntity, HttpStatus.CONFLICT);
				}else {
					User user = new User(each);
					user.setVerificationStatus(1);
					user.setStatus("Active");
					User savedUsers = userService.save(user);
					
					
					
					
					SecondaryUser secondaryUser=new SecondaryUser(); 
					//Set<SecondaryUser> secondaryUserList = createdByUser.get().getSecondaryUser();
//					if(secondaryUserList == null) {
//						secondaryUserList = new HashSet<>();
//					}
//					secondaryUserList.add(secondaryUser);
					
					User primaryuser =  new User(savedUsers.getCreatedByUser(),savedUsers.getCreatedByUser());
					secondaryUser.setPrimaryUser(primaryuser);
					secondaryUser.setSecondaryUser(savedUsers);
					
					secondaryUserService.save(secondaryUser);
				}
			}else {

				UserResponseEntity res=new UserResponseEntity(each.getEmail());	
//				System.out.println("RESPONSE" +res.getSchoolEducationBoard());
				   clas.add(res);
				
			}
			
			
			
////			List<User>	userFromDb = userService.findAllByOrganization(each.getOrganization());	
//			if(userFromDb.isEmpty()) {
//				User user = new User(each);
//				User holidays = userService.save(user);
//
//			
//		}else {
////		System.out.println("checking else");
//			UserResponseEntity res=new UserResponseEntity(userFromDb.get(0));	
////				System.out.println("RESPONSE" +res.getSchoolEducationBoard());
//				   clas.add(res);
//			
//		}
	
		}
		if(clas.isEmpty()) {
			response.setStatusCode(200);
			response.setDescription("Successfully Created");
			return new ResponseEntity(response, HttpStatus.OK);
		}else {
			 String descrption = null;
			 for(UserResponseEntity cl :  clas)
			 {     
//				 System.out.println("Before");
				 if(descrption != null) 
				 {
					 descrption = descrption +","+ cl.getName();
//					 
				 }else {

					 descrption = cl.getName();
				 }
				 System.out.println("Description "+ descrption);
			 }
			response.setStatusCode(409);
			response.setDescription(descrption +" " +" is Already exists");
			return new ResponseEntity(response, HttpStatus.CONFLICT);
		}
		
	}
	
	
	
	
	
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@PutMapping("/user-update/{userId}")
//	public ResponseEntity userUpdate(@RequestBody UserRequestEntity userRequestEntity) {
//		if (userRequestEntity.getUserId() == null) {
//			UserResponseEntity userResponseEntity = new UserResponseEntity();
//			userResponseEntity.setStatusCode(404);
//			userResponseEntity.setDescription("UserId Not Found");
//			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//		User userDatas = userService.findByUserId(userRequestEntity.getUserId());
//		if (userDatas != null) {
//			User users = new User(userRequestEntity,userDatas);
//			System.out.println("Users :" +users);
////			users.setCreatedByUser(userDatas.get().getCreatedByUser());
////			if (userRequestEntity.getAttachment() == null) {
////			} else {
////				if (userDatas.get().getAttachment() == null) {
////					Attachment attachment = new Attachment(userRequestEntity.getAttachment());
////					Attachment att = attachmentService.save(attachment);
////					String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
////							.path("/download_image/").path(att.getAttachmentId().toString()).toUriString();
////
////					String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
////							.path(att.getAttachmentId().toString()).toUriString();
////					att.setFileDownloadUrl(fileDownloadUrl);
////					att.setFileViewUrl(fileViewUrl);
////					users.setAttachment(att);
////				} else {
////					Attachment attachment = new Attachment(userRequestEntity.getAttachment());
////					users.setAttachment(attachment);
////				}
////			}
////			users.setPasscode(userDatas.get().getPasscode());
//			System.out.println("UserDatas "+ userDatas);
//			User userData = userService.save(users);
//			if (userData != null) {
//				
//				
//				UserResponseEntity userResponseEntity = new UserResponseEntity(userData);
////				UserType uTypes = userRequestEntity.getUserType();
////				for (UserType ut : userResponseEntity.getData().getUserType()) {
////					UserType userType = new UserType(ut, ut);
////					uTypes.add(userType);
////				}
////				userResponseEntity.setUserType(uTypes);
//				userResponseEntity.setStatusCode(200);
//				userResponseEntity.setDescription("User Updated Successfully");
//				return new ResponseEntity(userResponseEntity, HttpStatus.OK);
//			} else {
//				UserResponseEntity userResponseEntity = new UserResponseEntity();
//				userResponseEntity.setStatusCode(400);
//				userResponseEntity.setDescription("Unable to Update User");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//			}
//		} else {
//			UserResponseEntity userResponseEntity = new UserResponseEntity();
//				userResponseEntity.setStatusCode(404);
//				userResponseEntity.setDescription("User Not Found");
//				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//		}
//	}
//	
	
	
	
	
	
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/primaryuser")
	public ResponseEntity listPrimaryUser() {
		Set<UserType> userTypes = new HashSet<UserType>();
		UserType usertype=new UserType();
		usertype.setUserTypeId((long) 1);
		userTypes.add(usertype);
		List<User> userDatas = userService.findAllByUserTypeAndOrganization(userTypes,null);
		if (userDatas.isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(204);
			userResponseEntity.setMessage("No Data is Available");
			return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
		} else {
			List<UserResponseEntity> userResponseEntity1 = new ArrayList<UserResponseEntity>();
			for (User user : userDatas) {
				
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

					Set<UserType> userTypes1 = new HashSet<UserType>();
					for (UserType userType : userRequestEntity.getUserType()) {
						if (userType.getStatus() == null || userType.getStatus().equals("Active")
								|| userType.getStatus().equals("InActive") || userType.getStatus().equals("Pending")
								|| userType.getStatus().equals("Hold")) {
							UserType userType1 = new UserType(userType);
							userTypes1.add(userType1);
						}
					}

					userRequestEntity.setUserType(userTypes1);
					UserResponseEntity userResponseEntity = new UserResponseEntity(userRequestEntity);
					userResponseEntity1.add(userResponseEntity);
				
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
	
	
	
	
//	@GetMapping("/user/list/organization/{organizationId}")
//	private ResponseEntity getAllSubscribedClassForUsers(@PathVariable Integer organizationId){
//	
//		List<UserResponseEntity> response=new ArrayList<>();
//			Organization organizationIdFromDb=organizationService.findByOrganizationId(organizationId);
//				if(organizationIdFromDb != null)
//				{
//			
//					(userService.findByOrganization(organizationIdFromDb)).forEach(organization ->{
//	
//									response.add(new UserResponseEntity(organization));
//							
//	  
//					});
//		      
//					 if(response.isEmpty())
//					 {
//						 UserResponseEntity userResponseEntity = new UserResponseEntity();
//							userResponseEntity.setStatusCode(201);
//							userResponseEntity.setDescription("No Data is Available");
//							return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
//					 }
//					
//		
//				}else {
//					UserResponseEntity userResponseEntity = new UserResponseEntity();
//					userResponseEntity.setStatusCode(201);
//					userResponseEntity.setDescription("No Data  Not Found");
//					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
//				}
//	return new ResponseEntity(response, HttpStatus.OK);
//		}
	
	@GetMapping("/user/primaryUser/{primaryUserId}")
	public ResponseEntity listByPrimaryUser(@PathVariable("primaryUserId") Integer primaryUserId) {
		User user=new User(primaryUserId);
//		SecondaryUser org = new SecondaryUser(user);
		List<SecondaryUser> userDatas = secondaryUserService.findAllByPrimaryUser(user);
		if (userDatas.isEmpty()) {
			UserResEntity userResponseEntity = new UserResEntity();
			userResponseEntity.setErrorCode(204);
			userResponseEntity.setMessage("No Data is Available");
			return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
		} else {
			List<UserResponseEntity> userResponseEntity1 = new ArrayList<UserResponseEntity>();
			for (SecondaryUser each : userDatas) {
				
				if (each.getSecondaryUser().getStatus() == null || each.getSecondaryUser().getStatus().equals("Active") || each.getSecondaryUser().getStatus().equals("InActive")
						|| each.getSecondaryUser().getStatus().equals("Pending") || each.getSecondaryUser().getStatus().equals("Hold")) {
//					UserRequestEntity userRequestEntity = new UserRequestEntity(each, each.getSecondaryUser());
					UserResponseEntity userResponseEntity = new UserResponseEntity(each, each.getSecondaryUser());
					if (each.getSecondaryUser().getAttachment() == null) {

					} else {
						Attachment attachment = new Attachment(each.getSecondaryUser().getAttachment(),
								each.getSecondaryUser().getAttachment());
						if (attachment.getAttachmentByte() != null) {
							AttachmentByte attachmentByte = new AttachmentByte(attachment.getAttachmentByte());
							attachment.setAttachmentByte(attachmentByte);
						}

						each.getSecondaryUser().setAttachment(attachment);
					}
					if (each.getSecondaryUser().getOrganization() != null) {
						Organization organization = new Organization(each.getSecondaryUser().getOrganization(),
								each.getSecondaryUser().getOrganization().getOrganizationId());
						each.getSecondaryUser().setOrganization(organization);
					}

					Set<UserType> userTypes = new HashSet<UserType>();
					for (UserType userType : each.getSecondaryUser().getUserType()) {
						if (userType.getStatus() == null || userType.getStatus().equals("Active")
								|| userType.getStatus().equals("InActive") || userType.getStatus().equals("Pending")
								|| userType.getStatus().equals("Hold")) {
							UserType userType1 = new UserType(userType);
							userTypes.add(userType1);
						}
					}

					each.getSecondaryUser().setUserType(userTypes);
					
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
}
