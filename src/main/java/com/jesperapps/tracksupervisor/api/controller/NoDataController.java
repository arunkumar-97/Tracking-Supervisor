package com.jesperapps.tracksupervisor.api.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.entity.UserResEntity;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.AttachmentService;
import com.jesperapps.tracksupervisor.api.service.AttendanceService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class NoDataController {

//	@Autowired
//	public JavaMailSender emailSender;
	@Autowired
	private UserService userService;
	@Autowired
	private AttachmentService attachmentService;

	UserResponseEntity res = new UserResponseEntity();
	@Autowired
	private AttendanceService attendanceService;

//	@GetMapping("/nodata")
//	public ResponseEntity noDataAfterOneHour() throws ParseException {
//		List<UserResponseEntity> userResponseEntity1 = new ArrayList<UserResponseEntity>();
//		List<User> userData = userService.findAll();
//		if (userData.isEmpty()) {
//			UserResponseEntity userResponseEntity = new UserResponseEntity();
//			userResponseEntity.setErrorCode(404);
//			userResponseEntity.setMessage("Users Not Found");
//			userResponseEntity1.add(userResponseEntity);
//		} else {
//			
//			for (User user : userData) {
//				System.out.println("user" + user);
//				if (user.getAttendance().isEmpty()) {
//
//				} else {
//					List<Attendance> attendance = attendanceService.findAllByUser(user);
//					Attendance att = attendance.get(attendance.size() - 1);
//					String dateStart = att.getDate().toString()+" "+att.getTime().toString();
//					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					Date date = new Date();
//					String dateStop = dateFormat.format(date);
//					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					Date d1 = format.parse(dateStart);
//					Date d2 = format.parse(dateStop);
//					long diff = d2.getTime() - d1.getTime();
//					long diffHours = diff / (60 * 60 * 1000) % 24;
//					if(diffHours >= 1) {
//						UserResponseEntity userResponseEntity = new UserResponseEntity();
//						userResponseEntity.setErrorCode(404);
//						userResponseEntity.setMessage("No Data Found After 1hr, Send me the data for userId:"+user.getUserId());
//						userResponseEntity.setUserId(user.getUserId());
//						userResponseEntity1.add(userResponseEntity);
//						
//					}
//				}
//			}
//		}
//		return new ResponseEntity(userResponseEntity1, HttpStatus.CONFLICT);
//
//	}
}
