package com.jesperapps.tracksupervisor.api.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.entity.AttendanceResEntity;
import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceRequestEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.AttachmentService;
import com.jesperapps.tracksupervisor.api.service.AttendanceService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DataRequestController {
	
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private UserService userService;

	//update userstatus based on userid
	@PutMapping("/user/{userId}/{userStatus}")
	public ResponseEntity updateStatus(@PathVariable("userId") Long userId, @PathVariable("userStatus") String userStatus) {
		
		if (userId == null) {
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setErrorCode(404);
			userResponseEntity.setMessage("UserId Not Found");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}else {
			if(userStatus.equals("Active")) {
				Optional<User> userDatas = userService.findById(userId);
				if (userDatas.isPresent()) {
					User user = new User(userDatas.get(), userStatus);
					User userSaved = userService.save(user);
					if(userSaved != null) {
						UserResEntity userResponseEntity = new UserResEntity();
						userResponseEntity.setStatusCode(200);
						userResponseEntity.setDescription("Status Updated Successfully");
						return new ResponseEntity(userResponseEntity, HttpStatus.OK);
					}else {
						UserResEntity userResponseEntity = new UserResEntity();
						userResponseEntity.setErrorCode(400);
						userResponseEntity.setMessage("Unable to save User");
						return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
					}
				} else {
					UserResEntity userResponseEntity = new UserResEntity();
					userResponseEntity.setErrorCode(404);
					userResponseEntity.setMessage("User Not Found");
					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
				}	
			}else if(userStatus.equals("InActive")){
				System.out.println("InActive");
				Optional<User> userDatas = userService.findById(userId);
				if (userDatas.isPresent()) {
					User user = new User(userDatas.get(), userStatus, userStatus);
					System.out.println("user"+user);
					Attendance attendanceData = attendanceService.findByUserStatusAndUser("Active", user);
					System.out.println("attendanceData"+attendanceData);
					if(attendanceData == null) {
						UserResEntity userResponseEntity = new UserResEntity();
						userResponseEntity.setErrorCode(204);
						userResponseEntity.setMessage("No Data is Available");
						return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
					}else {
						attendanceData.setUserStatus("InActive");
						Set<Attendance> attSet = new HashSet<Attendance>();
						attSet.add(attendanceData);
						user.setAttendance(attSet);
						User userSaved = userService.save(user);
						if(userSaved != null) {
							UserResEntity userResponseEntity = new UserResEntity();
							userResponseEntity.setStatusCode(200);
							userResponseEntity.setDescription("Status Updated Successfully");
							return new ResponseEntity(userResponseEntity, HttpStatus.OK);
						}else {
							UserResEntity userResponseEntity = new UserResEntity();
							userResponseEntity.setErrorCode(400);
							userResponseEntity.setMessage("Unable to save User");
							return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
						}
					}
				
				} else {
					UserResEntity userResponseEntity = new UserResEntity();
					userResponseEntity.setErrorCode(404);
					userResponseEntity.setMessage("User Not Found");
					return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
				}	
			}
			
		}
		return null;
		
	}
	
//	@PostMapping("/active_attendance")
//	public ResponseEntity createTest(@RequestBody AttendanceRequestEntity attendanceReqEntity) {
//		Attendance att = attendanceService.findByUserStatusAndUser("Active",attendanceReqEntity.getUser());
//		if(att == null) {
//			Attendance attendance = new Attendance(attendanceReqEntity);
//			Attendance attendances = attendanceService.save(attendance);
//			if (attendances != null) {
//				AttendanceRequestEntity attendanceRequestEntity = new AttendanceRequestEntity(attendances);
//				AttendanceResEntity attendanceResEntity = new AttendanceResEntity(attendanceRequestEntity);
//				attendanceResEntity.setStatusCode(200);
//				attendanceResEntity.setErrorCode(null);
//				attendanceResEntity.setDescription("Attendance Created Successfully");
//				return new ResponseEntity(attendanceResEntity, HttpStatus.OK);
//			} else {
//				AttendanceResEntity userDataResEntity = new AttendanceResEntity();
//				userDataResEntity.setErrorCode(400);
//				userDataResEntity.setMessage("Unable to create Attendance");
//				return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
//			}
//		}else {
//			AttendanceResEntity userDataResEntity = new AttendanceResEntity();
//			userDataResEntity.setErrorCode(409);
//			userDataResEntity.setMessage("Attendance with Active UserStatus is Already Present");
//			return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
//		}
//		
//	}

	
//	@GetMapping("/user_by_userStatus/{userId}")
//	public ResponseEntity listUserByUserStatus(@PathVariable("userId") Long userId) {
//
//		User userDatas = userService.findByUserStatusAndUserId("Active",userId);
//		if(userDatas == null) {
//			UserResEntity userResEntity = new UserResEntity();
//			userResEntity.setErrorCode(204);
//			userResEntity.setMessage("No Data is Available");
//			return new ResponseEntity(userResEntity, HttpStatus.NOT_FOUND);
//		}else {
//			UserResponseEntity userResponseEntity = new UserResponseEntity(userDatas,userDatas);
//			return new ResponseEntity(userResponseEntity, HttpStatus.OK);
//		}
//	}
	
	//
	@GetMapping("/attendance_by_userStatus/{userId}")
	public ResponseEntity listUserByUserStatusOnlyIfAttendanceFoundForRequestedTime(@PathVariable("userId") Long userId) {
		User user = new User(userId, userId);
		Attendance attendanceDatas = attendanceService.findByUserStatusAndUser("Active",user);
		if(attendanceDatas == null) {
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(204);
			attendanceResEntity.setMessage("No Data is Available");
			return new ResponseEntity(attendanceResEntity, HttpStatus.NOT_FOUND);
		}else {
			AttendanceResponseEntity attendanceResponseEntity = new AttendanceResponseEntity(attendanceDatas,attendanceDatas);
			return new ResponseEntity(attendanceResponseEntity, HttpStatus.OK);
		}
	}

	
}
