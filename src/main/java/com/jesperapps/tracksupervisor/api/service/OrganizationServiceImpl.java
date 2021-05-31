package com.jesperapps.tracksupervisor.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.message.AdminOrgReqEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizataionWithUserRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationResponseEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationWithUserResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserRequestEntity;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.AttachmentByte;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private OtpSmsService otpService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private UserService userService;
	
	OrganizationWithUserResponseEntity res=new OrganizationWithUserResponseEntity();
	@Override
	public Organization save(Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	public Optional<Organization> findById(Integer organizationId) {
		// TODO Auto-generated method stub
		return organizationRepository.findById(organizationId);
	}

	@Override
	public Organization deactivateOrganization(Integer organizationId) {
		Optional<Organization> organization = organizationRepository.findById(organizationId);
		if (organization.isPresent()) {
			Organization dbOrganization = organization.get();
			Status status = new Status("InActive");
			dbOrganization.setStatus(status);
			return organizationRepository.save(dbOrganization);
		} else {
			return null;
		}
	}

	@Override
	public List<Organization> findAll() {
		// TODO Auto-generated method stub
		return organizationRepository.findAll();
	}

	@Override
	public Organization checkOrganization(String organizationName) {
		// TODO Auto-generated method stub
		return organizationRepository.findByOrganizationName(organizationName);
	}

	@Override
	public ResponseEntity createOrganization(OrganizataionWithUserRequestEntity orgRequest) {
		Organization organization=new Organization(orgRequest);
		Organization org=	this.checkOrganization(orgRequest.getOrganization().getOrganizationName());
			if(org != null) {
				ObjectNode jsonObject = objectMapper.createObjectNode();
				jsonObject.put("statusCode", res.SUCCESS);
				jsonObject.put("description", res.setDescription("Organization Already Exists"));
				return new ResponseEntity(jsonObject, HttpStatus.OK);
			}
			User userWithSameEmailId = userService.findUserByEmail(orgRequest.getUser().getEmail());
			if(userWithSameEmailId== null) {
				User	userWithPhNum=userService.findByPhoneNumber(orgRequest.getUser().getPhoneNumber());
				if(userWithPhNum != null) {
					ObjectNode jsonObject = objectMapper.createObjectNode();
					jsonObject.put("statusCode", res.SUCCESS);
					jsonObject.put("description", res.setDescription("User with PhoneNumber Already Exists"));
					return new ResponseEntity(jsonObject, HttpStatus.OK);
				}
			}else {
				ObjectNode jsonObject = objectMapper.createObjectNode();
				jsonObject.put("statusCode", res.SUCCESS);
				jsonObject.put("description", res.setDescription("Email Already Exists"));
				return new ResponseEntity(jsonObject, HttpStatus.CONFLICT);
			}
			
		Organization organizationFromDB=organizationRepository.save(organization);
			User user=	orgRequest.getUser();
			if(user !=null) {
				Organization organization2=new Organization(organizationFromDB.getOrganizationId());
				user.setOrganization(organization2);
				Optional<User> userResponse=userService.createUser(user);
				if(userResponse.isPresent()) {
					ObjectNode jsonObject = objectMapper.createObjectNode();
					jsonObject.put("statusCode", res.SUCCESS);
					jsonObject.put("description", res.setDescription("User and Organization Created Successfully"));
					return new ResponseEntity(jsonObject, HttpStatus.OK);
				}
				
			}
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", res.SUCCESS);
			jsonObject.put("description", res.setDescription("User and Organization Created Successfully"));
			return new ResponseEntity(jsonObject, HttpStatus.OK);
		
	}

	@Override
	public Organization addOrganization(OrganizationRequestEntity organizationRequestEntity) {
		Organization organization = new Organization(organizationRequestEntity,organizationRequestEntity);
		if(organization.getAttachment() == null) {
		organizationRepository.save(organization);
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
			
//			organizationRepository.save(organization);
//
//			} else {
//				organizationService.save(organization);
//			}
		}
		return organization;
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Organization organization=new Organization(organizationRequestEntity);
//		System.out.println("Organization" + organization.toString());
//		organizationRepository.save(organization);
//		
//					if(organizationRequestEntity.getAttachment() == null) {
//						
//					}else {
//						System.out.println("Attachment" + organizationRequestEntity.getAttachment());
//						Attachment att = organizationRequestEntity.getAttachment();
//						if(att != null) {
//							AttachmentByte attachmentByte=att.getAttachmentByte();
//							System.out.println("att" + att.getAttachmentId());
//
//							if(attachmentByte !=null) {
//								attachmentByte.setAttachment(att);
////							att.setAttachmentByte(attachmentByte);
//							}
//							organization.setAttachment(att);
//							att.setOrganization(organization);
////							att.setAttachmentByte(att.getAttachmentByte());
//						Attachment		attachment=attachmentService.save(att);
//						System.out.println("Not Null" + attachment);
//						if (attachment != null) {
//							String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download_image/")
//									.path(attachment.getAttachmentId().toString()).toUriString();
//
//							String fileViewUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/view_image/")
//									.path(attachment.getAttachmentId().toString()).toUriString();
//							attachment.setFileDownloadUrl(fileDownloadUrl);
//							attachment.setFileViewUrl(fileViewUrl);
//							attachmentService.save(attachment);
//						}
//							
//						}
////						organization.setAttachment(att);
////						att.setOrganization(organization);
////							attachmentService.save(att);
//						
//					organizationRepository.save(organization);
//			//
////						} else {
////							organizationService.save(organization);
////						}
//					}
//					
//					
//					
//					
//					return organization;
					
		
		
		
		
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

	@Override
	public Organization findByOrganizationName(String organizationName) {
		// TODO Auto-generated method stub
		return organizationRepository.findByOrganizationName(organizationName);
	}

	@Override
	public Organization findByOrganizationId(Integer organizationId) {
		// TODO Auto-generated method stub
		return organizationRepository.findByOrganizationId(organizationId);
	}

	@Override
	public Organization addOrganization(AdminOrgReqEntity organizationRequestEntity) {
		Organization organization = new Organization(organizationRequestEntity,organizationRequestEntity);
		organizationRepository.save(organization);
		return organization;
	}


}
