package com.jesperapps.tracksupervisor.api.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.model.OtpSms;
import com.jesperapps.tracksupervisor.api.model.User;
//import com.jesperapps.schoolmanagement.api.message.Response;
//import com.jesperapps.schoolmanagement.api.model.OtpSms;
//import com.jesperapps.schoolmanagement.api.model.User;
import com.jesperapps.tracksupervisor.api.service.OtpSmsService;
//import com.sun.el.parser.ParseException;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
public class OtpSmsController {
	
	@Autowired
	private OtpSmsService otpSmsService;
	
	@Autowired
	private UserService userRegistrationService;
	
	
	@SuppressWarnings({ "rawtypes" })
	@GetMapping("/validate_otp/{otp}/{phone}")
	public ResponseEntity validateOtp(@PathVariable("otp") int otp, @PathVariable("phone") String phoneNumber)
			throws ParseException {
//		System.out.println("otp" + otp + "phone" + phoneNumber);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String otpValidationTime = dtf.format(now);
		if (otp >= 0) {
 
//			otpSmsService.clearOTP(phoneNumber);
			Optional<OtpSms> Otp = otpSmsService.getOtp(phoneNumber);
			System.out.println("otp  :" + Otp );
			if (Otp == null) {
//				ObjectNode jsonObject = objectMapper.createObjectNode();
//				jsonObject.put("statusCode", res.FAILURE);
//				jsonObject.put("message", res.setDescription("Otp not found"));
//				return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"Otp not found"));
			} else if (Otp.get().getOtp() > 0) {
				if (otp == Otp.get().getOtp()) {
					try {
						if (format.parse(otpValidationTime).getTime() - format.parse(Otp.get().getOtpGenerationTime())
								.getTime() < TimeUnit.MINUTES.toMillis(Otp.get().getExpireMins())) {
							otpSmsService.clearOTP(phoneNumber);

						} else {
							otpSmsService.clearOTP(phoneNumber);
//							System.out.println("else");
//						ObjectNode jsonObject = objectMapper.createObjectNode();
//						jsonObject.put("statusCode", res.FAILURE);
//						jsonObject.put("message", res.setDescription("otp expired"));
//						return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"otp expired"));

						}
					} catch (java.text.ParseException e) {
						//  
						e.printStackTrace();
					}

				} else {
//					ObjectNode jsonObject = objectMapper.createObjectNode();
//					jsonObject.put("statusCode", res.FAILURE);
//					jsonObject.put("message", res.setDescription("Entered Otp is NOT valid. Please Retry!"));
//					return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"Entered Otp is NOT valid. Please Retry!"));

				}
			} else {
//				ObjectNode jsonObject = objectMapper.createObjectNode();
//				jsonObject.put("statusCode", res.FAILURE);
//				jsonObject.put("message", res.setDescription("Invalid Otp"));
//				return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"Invalid Otp"));

			}
		}else {
//			ObjectNode jsonObject = objectMapper.createObjectNode();
//			jsonObject.put("statusCode", res.FAILURE);
//			jsonObject.put("message", res.setDescription("Entered Otp is invalid"));
//			return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"Entered Otp is NOT valid. Please Retry!"));

		}
		Optional<User> phone = userRegistrationService.findByPhoneNumber(phoneNumber);
//		System.out.println("phone" + phone);
		if (phone.isPresent()) {
//			phone.get().setOtpVerification("Verified");
			@SuppressWarnings("unused")
//		User user = userRegistrationService.save(phone.get());
////			user.setVerificationStatus(1);
//			userRegistrationService.save(user);
			
			
			User user = userRegistrationService.save(phone.get());
			user.setVerificationStatus(1);
			userRegistrationService.save(user);
//			UserResponseEntity userResponseEntity = new UserResponseEntity(user, user, user);
//			userResponseEntity.setStatusCode(res.SUCCESS);
//			userResponseEntity.setDescription("Entered Otp is valid");
//			return new ResponseEntity(userResponseEntity, HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(new Response(200,"Entered Otp is valid"));
		} else {
//			ObjectNode jsonObject = objectMapper.createObjectNode();
//			jsonObject.put("statusCode", res.FAILURE);
//			jsonObject.put("message", res.setDescription(phone + " not registered"));
//			return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409, phone + "not registered"));

	}
	
	
	
	
	@SuppressWarnings({ "rawtypes"})
	@GetMapping("/generate_otp/{phone}")
	public ResponseEntity sendSMS(@PathVariable("phone") String phone) {
//		System.out.println("phoneNumber" + phone);
		// for(String phone : otpSmsRequestEntity.getPhoneNumber()) {
		// System.out.println("phones"+phone);
		Optional<User> phoneNumber = userRegistrationService.findByPhoneNumber(phone);
//		System.out.println("phoneNumbers" + phoneNumber);
		if (phoneNumber.isPresent()) {
//			System.out.println("phone present");
				List<OtpSms> otpSms = otpSmsService.findAll();
				if (otpSms.isEmpty()) {
//					System.out.println("otp empty");
					int otp = otpSmsService.generateOTP(phoneNumber.get().getPhoneNumber());
					if (otp == 0) {
//						ObjectNode jsonObject = objectMapper.createObjectNode();
//						jsonObject.put("statusCode", res.FAILURE);
//						jsonObject.put("message", res.setDescription("unable to generate otp"));
//						return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"unable to generate otp"));
					} else {

						try {
//							System.out.println("try");
//							Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//							Message message = Message.creator(new com.twilio.type.PhoneNumber(phone),
//									new com.twilio.type.PhoneNumber(TWILIO_NUMBER), "Your One Time Password(OTP) is " + otp)
//									.create();
							userRegistrationService.sendSms("Your One Time Password(OTP) is " + otp , phone);
//							UserResponseEntity userResponseEntity = new UserResponseEntity(phoneNumber.get().getCity(), phoneNumber.get());
//							userResponseEntity.setStatusCode(res.SUCCESS);
//							userResponseEntity.setDescription("Otp sent successfully ");
//							return new ResponseEntity(userResponseEntity, HttpStatus.OK);
							return ResponseEntity.status(HttpStatus.OK).body(new Response(200,"Otp sent successfully "));

						} catch (Exception e) {
//							System.out.println("e" + e);
//							ObjectNode jsonObject = objectMapper.createObjectNode();
//							jsonObject.put("statusCode", res.FAILURE);
//							jsonObject.put("message", res.setDescription("unable to send otp"));
//							return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"unable to send otp"));

						}

					}
				} else {
//					System.out.println("else otp empty");
					otpSmsService.clearOTP(phone);
					int otp = otpSmsService.generateOTP(phoneNumber.get().getPhoneNumber());
					if (otp == 0) {
//						ObjectNode jsonObject = objectMapper.createObjectNode();
//						jsonObject.put("statusCode", res.FAILURE);
//						jsonObject.put("message", res.setDescription("unable to generate otp"));
//						return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"unable to generate otp"));

					} else {
//						System.out.println("eellssee");

						try {
//							System.out.println("try");
//							Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//							Message message = Message.creator(new com.twilio.type.PhoneNumber(phone),
//									new com.twilio.type.PhoneNumber(TWILIO_NUMBER), "Your One Time Password(OTP) is " + otp)
//									.create();
							userRegistrationService.sendSms("Your One Time Password(OTP) is " + otp , phone);
//							UserResponseEntity userResponseEntity = new UserResponseEntity(phoneNumber.get().getCity(), phoneNumber.get());
//							userResponseEntity.setStatusCode(res.SUCCESS);
//							userResponseEntity.setDescription("Otp sent successfully ");
//							return new ResponseEntity(userResponseEntity, HttpStatus.OK);
							return ResponseEntity.status(HttpStatus.OK).body(new Response(200,"Otp sent successfully "));


						} catch (Exception e) {
//							System.out.println("ex" + e);
//							ObjectNode jsonObject = objectMapper.createObjectNode();
//							jsonObject.put("statusCode", res.FAILURE);
//							jsonObject.put("message", res.setDescription("unable to send otp"));
//							return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"unable to send otp"));

						}

					}
				}
			

		} else {
//			ObjectNode jsonObject = objectMapper.createObjectNode();
//			jsonObject.put("statusCode", res.FAILURE);
//			jsonObject.put("message", res.setDescription(phone + " not registered"));
//			return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
		}
		// }
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(409,"unable to send otp"));

		/*
		 * For call Call call = Call.creator( new
		 * com.twilio.type.PhoneNumber("+917598193103"), new
		 * com.twilio.type.PhoneNumber("+15176804129"),
		 * URI.create("http://demo.twilio.com/docs/voice.xml")) .create();
		 */
	}
	


}
