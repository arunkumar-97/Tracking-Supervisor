package com.jesperapps.tracksupervisor.api.controller;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.entity.AttendanceResEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceResponseEntity;
import com.jesperapps.tracksupervisor.api.message.ReportsRequestEntity;
import com.jesperapps.tracksupervisor.api.message.ReportsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.Reports;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.service.ReportsService;
import com.jesperapps.tracksupervisor.api.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ReportsController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportsService reportsService;
	
	@PostMapping("/reports")
	public ReportsResponseEntity createReportsForUser(@RequestBody ReportsRequestEntity reportsRequestEntity) {
		
		System.out.println("UserId" + reportsRequestEntity.getUser().getUserId());
		
		Reports reports=new Reports(reportsRequestEntity);
		System.out.println("report UserId" + reports.getUser().getUserId());
		Reports savedReports=reportsService.save(reports);
		if(savedReports != null) {
			ReportsResponseEntity response1=new ReportsResponseEntity(200,"Reports Successfully Created");
			return response1;
		}else {
			ReportsResponseEntity response2=new ReportsResponseEntity(409,"Reports Unable to Create");
			return response2;

		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/reports/{fromDate}/{toDate}/{userId}")
	public ResponseEntity ListAttendanceByDate(@PathVariable("fromDate") Date fromDate,
			@PathVariable("toDate") Date toDate, @PathVariable Long userId) {
		System.out.println("fromDate" + fromDate);

		Optional<User> userData = userService.findById(userId);
		if (userData == null) {
			System.out.println("ifnull");
			AttendanceResEntity attendanceResEntity = new AttendanceResEntity();
			attendanceResEntity.setErrorCode(204);
			attendanceResEntity.setMessage("No Data is Available");
			return new ResponseEntity(attendanceResEntity, HttpStatus.NOT_FOUND);
		} else {
		
				if (userData.get().getStatus() == null || userData.get().getStatus().equals("Active") || userData.get().getStatus().equals("InActive")) {
					userId = userData.get().getUserId();
					System.out.println("userId" + userId);
				}
			
		}
		User user = new User(userId, userId);

		List<Reports> reports = reportsService.findAllByUserAndDateBetween(user, fromDate, toDate);
		System.out.println("attendance" + reports);
		if (reports.isEmpty()) {
			System.out.println("i");
			ReportsResponseEntity reportsResponseEntity = new ReportsResponseEntity();
			reportsResponseEntity.setErrorCode(204);
			reportsResponseEntity.setMessage("No Data is Available");
			return new ResponseEntity(reportsResponseEntity, HttpStatus.NOT_FOUND);
		} else {
			List<ReportsResponseEntity> attendanceResponseEntity1 = new ArrayList<ReportsResponseEntity>();
			for (Reports report : reports) {
				System.out.println("for");
				if (report.getStatus() == null || report.getStatus().equals("Active") || report.getStatus().equals("InActive")
						|| report.getStatus().equals("Pending")) {
					System.out.println("aa");
					ReportsResponseEntity att = new ReportsResponseEntity(report);
					User user1 = new User(att.getUser());
					att.setUser(user1);
					attendanceResponseEntity1.add(att);
					System.out.println("attendanceResponseEntity1" + attendanceResponseEntity1);
				}
			}
			if (attendanceResponseEntity1.isEmpty()) {
				System.out.println("iff");
				ReportsResponseEntity attendanceResEntity = new ReportsResponseEntity();
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
