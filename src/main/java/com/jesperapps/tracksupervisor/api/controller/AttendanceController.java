package com.jesperapps.tracksupervisor.api.controller;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jesperapps.tracksupervisor.api.entity.AttendanceResEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceRequestEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;
import com.jesperapps.tracksupervisor.api.service.AttendanceService;
import com.jesperapps.tracksupervisor.api.service.UserService;
import com.jesperapps.tracksupervisor.api.service.WorkPlaceService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AttendanceController {

	@Autowired
	private UserService userService;
	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private WorkPlaceService workPlaceService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/attendance")
	public ResponseEntity createTest(@RequestBody AttendanceRequestEntity attendanceReqEntity) {
		List<Attendance> attList = attendanceService.findAllByDateAndAddressAndUser(attendanceReqEntity.getDate(),
				attendanceReqEntity.getAddress(), attendanceReqEntity.getUser());
		if (attList.isEmpty()) {

		} else {
			AttendanceResEntity attendanceResponseEntity = new AttendanceResEntity();
			attendanceResponseEntity.setErrorCode(409);
			attendanceResponseEntity.setMessage("Attendance already exists");
			return new ResponseEntity(attendanceResponseEntity, HttpStatus.CONFLICT);
		}
		if (attendanceReqEntity.getDate() == null) {
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(400);
			attendanceResEntity.setMessage("Date can't be empty");
			return new ResponseEntity(attendanceResEntity, HttpStatus.CONFLICT);
		}
		if (attendanceReqEntity.getTime() == null) {
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(400);
			attendanceResEntity.setMessage("Time can't be empty");
			return new ResponseEntity(attendanceResEntity, HttpStatus.CONFLICT);
		}

		if (attendanceReqEntity.getLatitude().equals(0.0)) {
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(400);
			attendanceResEntity.setMessage("Latitude can't be empty");
			return new ResponseEntity(attendanceResEntity, HttpStatus.CONFLICT);
		}
		if (attendanceReqEntity.getLongitude().equals(0.0)) {
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(400);
			attendanceResEntity.setMessage("Longitude can't be empty");
			return new ResponseEntity(attendanceResEntity, HttpStatus.CONFLICT);
		}
		System.out.println(attendanceReqEntity);

		List<WorkPlace> workPlaceList = workPlaceService.findAllByAssignedToUser(attendanceReqEntity.getUser());
		System.out.println("workPlaceList"+workPlaceList);
		if (workPlaceList.isEmpty()) {
			System.out.println("if");
			AttendanceResEntity userDataResEntity = new AttendanceResEntity();
			userDataResEntity.setErrorCode(400);
			userDataResEntity.setMessage("WorkPlace Not Assigned");
			return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
		} else {
			System.out.println("else");
			for (WorkPlace wp : workPlaceList) {
				System.out.println("wp"+wp);
//				if (wp.getToDate().compareTo(attendanceReqEntity.getDate())
//						* attendanceReqEntity.getDate().compareTo(wp.getFromDate()) >= 0) {
				if(attendanceReqEntity.getDate().after(wp.getFromDate()) 
						|| attendanceReqEntity.getDate().before(wp.getToDate())){
					System.out.println("iff");
					for (Address add : wp.getAddress()) {
						int distance = 2;
//						Scanner input = new Scanner(System.in);
//				        System.out.print("Input the latitude of coordinate 1: ");
//				        double lat1 = input.nextDouble();
//				        System.out.print("Input the longitude of coordinate 1: ");
//				        double lon1 = input.nextDouble();
//				        System.out.print("Input the latitude of coordinate 2: ");
//				        double lat2 = input.nextDouble();
//				        System.out.print("Input the longitude of coordinate 2: ");
//				        double lon2 = input.nextDouble();

						double lat1 = Math.toRadians(attendanceReqEntity.getLatitude());
						double lon1 = Math.toRadians(attendanceReqEntity.getLongitude());
						double lat2 = Math.toRadians(add.getLatitude());
						double lon2 = Math.toRadians(add.getLongitude());

				        double earthRadius = 6371.01; //Kilometers
				        double dist = earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
				        System.out.println("dist"+dist);
				        if (dist <= distance) {
							Attendance attendance = new Attendance(attendanceReqEntity);
							Attendance attendances = attendanceService.save(attendance);
							if (attendances != null) {
								AttendanceRequestEntity attendanceRequestEntity = new AttendanceRequestEntity(
										attendances);
								AttendanceResEntity attendanceResEntity = new AttendanceResEntity(
										attendanceRequestEntity);
								attendanceResEntity.setStatusCode(200);
								attendanceResEntity.setErrorCode(null);
								attendanceResEntity.setDescription("Attendance Created Successfully");
								return new ResponseEntity(attendanceResEntity, HttpStatus.OK);
							} else {
								AttendanceResEntity userDataResEntity = new AttendanceResEntity();
								userDataResEntity.setErrorCode(400);
								userDataResEntity.setMessage("Unable to create Attendance");
								return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
							}
						} else {
//							AttendanceResEntity userDataResEntity = new AttendanceResEntity();
//							userDataResEntity.setErrorCode(400);
//							userDataResEntity.setMessage("Distance exceeded 2km");
//							return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
						}
					}
				} else {
					
				}
			}
			
//			System.out.println("else1");
//			AttendanceResEntity userDataResEntity = new AttendanceResEntity();
//			userDataResEntity.setErrorCode(400);
//			userDataResEntity.setMessage("WorkPlace Not Assigned");
//			return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
		}

		return null;
	}
	
	 public static double distance_Between_LatLong(double lat1, double lon1, double lat2, double lon2) {
	        lat1 = Math.toRadians(lat1);
	        lon1 = Math.toRadians(lon1);
	        lat2 = Math.toRadians(lat2);
	        lon2 = Math.toRadians(lon2);

	        double earthRadius = 6371.01; //Kilometers
	        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
	    }


//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@GetMapping("/attendance/{fromDate}/{toDate}")
//	public ResponseEntity ListAttendanceByDate(@PathVariable("fromDate") Date fromDate,
//			@PathVariable("toDate") Date toDate) {
//		List<User> user = userService.findAll();
//		if (user.isEmpty()) {
//			UserResEntity userResEntity = new UserResEntity();
//			userResEntity.setErrorCode(204);
//			userResEntity.setMessage("No Data is Available");
//			return new ResponseEntity(userResEntity, HttpStatus.NOT_FOUND);
//		} else {
//			List<UserResponseEntity> userResponseEntity1 = new ArrayList<UserResponseEntity>();
//			for (User user1 : user) {
//				if (user1.getStatus() == null || user1.getStatus().equals("Active")
//						|| user1.getStatus().equals("InActive") || user1.getStatus().equals("Pending")) {
//					UserResponseEntity userData = new UserResponseEntity(user1);
//					if (userData.getAttachment() == null) {
//					} else {
//						Attachment attachment = new Attachment(userData.getAttachment(), userData.getAttachment(),
//								userData.getAttachment());
//						userData.setAttachment(attachment);
//					}
//					Set<Attendance> attSet = new HashSet<Attendance>();
//					List<Attendance> attendance = attendanceService.findAllByUserAndDateBetween(user1, fromDate,
//							toDate);
//					for (Attendance at : attendance) {
//						Attendance attend = new Attendance(at);
//						attSet.add(attend);
//
//					}
//					userData.setAttendance(attSet);
//					userResponseEntity1.add(userData);
//				}
//			}
//			if (userResponseEntity1.isEmpty()) {
//				UserResEntity userResEntity = new UserResEntity();
//				userResEntity.setErrorCode(204);
//				userResEntity.setMessage("No Data is Available");
//				return new ResponseEntity(userResEntity, HttpStatus.NOT_FOUND);
//			} else {
//				return new ResponseEntity(userResponseEntity1, HttpStatus.OK);
//			}
//		}
//	}

	Long userId;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/attendance/{fromDate}/{toDate}/{phoneNumber}")
	public ResponseEntity ListAttendanceByDate(@PathVariable("fromDate") Date fromDate,
			@PathVariable("toDate") Date toDate, @PathVariable("phoneNumber") String phoneNumber) {
		System.out.println("fromDate" + fromDate);

		List<User> userData = userService.findAllByPhoneNumber(phoneNumber);
		if (userData == null) {
			System.out.println("ifnull");
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(204);
			attendanceResEntity.setMessage("No Data is Available");
			return new ResponseEntity(attendanceResEntity, HttpStatus.NOT_FOUND);
		} else {
			for (User usr : userData) {
				if (usr.getStatus() == null || usr.getStatus().equals("Active") || usr.getStatus().equals("InActive")) {
					userId = usr.getUserId();
					System.out.println("userId" + userId);
				}
			}
		}
		User user = new User(userId, userId);

		List<Attendance> attendance = attendanceService.findAllByUserAndDateBetween(user, fromDate, toDate);
		System.out.println("attendance" + attendance);
		if (attendance.isEmpty()) {
			System.out.println("i");
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(204);
			attendanceResEntity.setMessage("No Data is Available");
			return new ResponseEntity(attendanceResEntity, HttpStatus.NOT_FOUND);
		} else {
			List<AttendanceResponseEntity> attendanceResponseEntity1 = new ArrayList<AttendanceResponseEntity>();
			for (Attendance att1 : attendance) {
				System.out.println("for");
				if (att1.getStatus() == null || att1.getStatus().equals("Active") || att1.getStatus().equals("InActive")
						|| att1.getStatus().equals("Pending")) {
					System.out.println("aa");
					AttendanceResponseEntity att = new AttendanceResponseEntity(att1);
					User user1 = new User(att.getUser());
					 att.setUser(user1);
					attendanceResponseEntity1.add(att);
					System.out.println("attendanceResponseEntity1" + attendanceResponseEntity1);
				}
			}
			if (attendanceResponseEntity1.isEmpty()) {
				System.out.println("iff");
				AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
				attendanceResEntity.setErrorCode(204);
				attendanceResEntity.setMessage("No Data is Available");
				return new ResponseEntity(attendanceResEntity, HttpStatus.NOT_FOUND);
			} else {
				System.out.println("ell");
				return new ResponseEntity(attendanceResponseEntity1, HttpStatus.OK);
			}
		}

	}

}
