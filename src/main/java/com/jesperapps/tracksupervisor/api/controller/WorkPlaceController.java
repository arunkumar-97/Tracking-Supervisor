package com.jesperapps.tracksupervisor.api.controller;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


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

import com.jesperapps.tracksupervisor.api.entity.AttendanceResEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceRequestEntity;
import com.jesperapps.tracksupervisor.api.message.WorkPlaceRequestEntity;
import com.jesperapps.tracksupervisor.api.message.WorkPlaceResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;
import com.jesperapps.tracksupervisor.api.model.WorkersCount;
import com.jesperapps.tracksupervisor.api.repository.WorkPlaceRepository;
import com.jesperapps.tracksupervisor.api.service.AddressService;
import com.jesperapps.tracksupervisor.api.service.WorkPlaceService;
import com.jesperapps.tracksupervisor.api.service.WorkersCountService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class WorkPlaceController {

	@Autowired
	private WorkPlaceService workPlaceService;
	@Autowired
	private AddressService addressService;

	@Autowired
	private WorkersCountService workersCountService;

	@PostMapping("/work-place")
	public ResponseEntity createWorkPlace(@RequestBody WorkPlaceRequestEntity workPlaceReqEntity) {
		

		if (workPlaceReqEntity.getFromDate() == null) {
			WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
			workPlaceResponseEntity.setErrorCode(400);
			workPlaceResponseEntity.setMessage("FromDate can't be empty");
			return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
		}
		if (workPlaceReqEntity.getToDate() == null) {
			WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
			workPlaceResponseEntity.setErrorCode(400);
			workPlaceResponseEntity.setMessage("ToDate can't be empty");
			return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
		}
		if (workPlaceReqEntity.getAddress() == null || workPlaceReqEntity.getAddress().isEmpty()) {
			WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
			workPlaceResponseEntity.setErrorCode(400);
			workPlaceResponseEntity.setMessage("Address can't be empty");
			return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
		} else {
			for (Address address : workPlaceReqEntity.getAddress()) {
				if (address.getLatitude().equals(0.0)) {
					WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
					workPlaceResponseEntity.setErrorCode(400);
					workPlaceResponseEntity.setMessage("Latitude can't be empty");
					return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
				}
				if (address.getLongitude().equals(0.0)) {
					WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
					workPlaceResponseEntity.setErrorCode(400);
					workPlaceResponseEntity.setMessage("Longitude can't be empty");
					return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
				}

				if (address.getAddressName() == null || address.getAddressName().isEmpty()) {
					WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
					workPlaceResponseEntity.setErrorCode(400);
					workPlaceResponseEntity.setMessage("Address can't be empty");
					return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
				}
			}

		}
	WorkPlace	workPlaceFromDb=workPlaceService.findByAssignedToUserAndFromDateAndToDate(workPlaceReqEntity.getAssignedToUser(),workPlaceReqEntity.getFromDate(),workPlaceReqEntity.getToDate());
		if(workPlaceFromDb==null) {
			WorkPlace workPlace = new WorkPlace(workPlaceReqEntity);
			WorkPlace workPlaces = workPlaceService.save(workPlace);
			if (workPlaces != null) {

				WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
				workPlaceResponseEntity.setStatusCode(200);
				workPlaceResponseEntity.setErrorCode(null);
				workPlaceResponseEntity.setDescription("WorkPlace Added Successfully");
				return new ResponseEntity(workPlaceResponseEntity, HttpStatus.OK);
			} else {
				WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
				workPlaceResponseEntity.setErrorCode(400);
				workPlaceResponseEntity.setMessage("Unable to add WorkPlace");
				return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
			}
		}else {
			WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
			workPlaceResponseEntity.setErrorCode(400);
			workPlaceResponseEntity.setMessage("WorkPlace Already Assigned to the User");
			return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/multiple/work-place")
	public ResponseEntity createWorkPlace(@RequestBody List<WorkPlaceRequestEntity> workPlaceReqEntity) {
		WorkPlaceResponseEntity response=new WorkPlaceResponseEntity();
		List<WorkPlaceResponseEntity> clas=new ArrayList<>();
		for(WorkPlaceRequestEntity each:workPlaceReqEntity) {

			if (each.getFromDate() == null) {
				WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
				workPlaceResponseEntity.setErrorCode(400);
				workPlaceResponseEntity.setMessage("FromDate can't be empty");
				return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
			}
			if (each.getToDate() == null) {
				WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
				workPlaceResponseEntity.setErrorCode(400);
				workPlaceResponseEntity.setMessage("ToDate can't be empty");
				return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
			}
			if (each.getAddress() == null || each.getAddress().isEmpty()) {
				WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
				workPlaceResponseEntity.setErrorCode(400);
				workPlaceResponseEntity.setMessage("Address can't be empty");
				return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
			} else {
				for (Address address : each.getAddress()) {
					if (address.getLatitude().equals(0.0)) {
						WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
						workPlaceResponseEntity.setErrorCode(400);
						workPlaceResponseEntity.setMessage("Latitude can't be empty");
						return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
					}
					if (address.getLongitude().equals(0.0)) {
						WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
						workPlaceResponseEntity.setErrorCode(400);
						workPlaceResponseEntity.setMessage("Longitude can't be empty");
						return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
					}

					if (address.getAddressName() == null || address.getAddressName().isEmpty()) {
						WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
						workPlaceResponseEntity.setErrorCode(400);
						workPlaceResponseEntity.setMessage("Address can't be empty");
						return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
					}
				}

			}
			
			List<WorkPlace>	workPlaceFromDb=workPlaceService.findAllByAssignedToUserAndFromDateAndToDate(each.getAssignedToUser(),each.getFromDate(),each.getToDate());
			if(workPlaceFromDb.isEmpty()) {
				WorkPlace workPlace = new WorkPlace(each);
				WorkPlace workPlaces = workPlaceService.save(workPlace);

			
		}else {
//		System.out.println("checking else");
				WorkPlaceResponseEntity res=new WorkPlaceResponseEntity(workPlaceFromDb.get(0));	
//				System.out.println("RESPONSE" +res.getSchoolEducationBoard());
				   clas.add(res);
			
		}
	
		}
		if(clas.isEmpty()) {
			response.setStatusCode(200);
			response.setDescription("Successfully Created");
			return new ResponseEntity(response, HttpStatus.OK);
		}else {
			 String descrption = null;
			 for(WorkPlaceResponseEntity cl :  clas)
			 {     
//				 System.out.println("Before");
				 if(descrption != null) 
				 {
//					 System.out.println("if");
					 descrption = descrption +","+ cl.getAssignedToUser().getName();
				 }else {
					 descrption =   cl.getAssignedToUser().getName();
				 }
				 System.out.println("Description "+ descrption);
			 }
			response.setStatusCode(409);
			response.setDescription(descrption +" " +"workplace  is Already Assigned for the User");
			return new ResponseEntity(response, HttpStatus.CONFLICT);
		}
		
	}


	@PutMapping("/work-place/{supervisorUserId}/{noOfWorkers}/{date}/{address}/{noOfWorkersUpdatedByUserId}")
	public ResponseEntity updateWorkPlace(@PathVariable("supervisorUserId") Long supervisorUserId,
			@PathVariable("noOfWorkers") int noOfWorkers, @PathVariable("date") Date date,
			@PathVariable("address") String address,
			@PathVariable("noOfWorkersUpdatedByUserId") Long noOfWorkersUpdatedByUserId) {

		User assignedToUserId = new User(supervisorUserId, supervisorUserId);
		List<WorkPlace> workPlace = workPlaceService.findAllByAssignedToUser(assignedToUserId);
		for (WorkPlace wp : workPlace) {
			if ((wp.getFromDate().compareTo(date) * date.compareTo(wp.getToDate()) >= 0)
					|| (wp.getFromDate().compareTo(date) * date.compareTo(wp.getToDate()) >= 0)) {
				Address add = addressService.findByWorkPlaceAndAddressName(wp, address);
				System.out.println("add" + add);
				User noOfWorkersUpdatedBy = new User(noOfWorkersUpdatedByUserId, noOfWorkersUpdatedByUserId);
				WorkersCount workersCount = new WorkersCount(noOfWorkers, date, add, noOfWorkersUpdatedBy);
				WorkersCount wcData = workersCountService.findByNoOfWorkersAndDateAndAddressAndNoOfWorkersUpdatedByUser(
						noOfWorkers, date, add, noOfWorkersUpdatedBy);
				if (wcData == null) {
					WorkersCount wc = workersCountService.save(workersCount);
					WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
					workPlaceResponseEntity.setStatusCode(200);
					workPlaceResponseEntity.setErrorCode(null);
					workPlaceResponseEntity.setDescription("No of Workers Updated Successfully");
					return new ResponseEntity(workPlaceResponseEntity, HttpStatus.OK);
				} else {
					WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
					workPlaceResponseEntity.setErrorCode(409);
					workPlaceResponseEntity.setMessage("No of Workers already updated");
					return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
				}

			}
//			WorkPlace workPlaceData = workPlaceService.findByFromDateIsBetween(date,date);
//			System.out.println("workPlaceData"+workPlaceData);
//			if(workPlace != null){
//				
//			}else {
//				
//			}
		}

		return null;

	}

	@GetMapping("/work-place/{managerUserId}/{supervisorUserId}")
	public ResponseEntity ListWorkPlaceByManagerUserIdAndSupervisorUserId(
			@PathVariable("managerUserId") Long managerUserId,
			@PathVariable("supervisorUserId") Long supervisorUserId) {
		User assignedFromUser = new User(managerUserId, managerUserId);
		User assignedToUser = new User(supervisorUserId, supervisorUserId);
		List<WorkPlace> workPlace = workPlaceService.findAllByAssignedFromUserAndAssignedToUser(assignedFromUser,
				assignedToUser);
		System.out.println("Work Place :" +workPlace);
		if (workPlace.isEmpty()) {
			WorkPlaceResponseEntity workPlaceResEntity = new WorkPlaceResponseEntity();
			workPlaceResEntity.setErrorCode(204);
			workPlaceResEntity.setMessage("No Data is Available");
			return new ResponseEntity(workPlaceResEntity, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("else");
			List<WorkPlaceResponseEntity> workPlaceResponseEntity1 = new ArrayList<WorkPlaceResponseEntity>();
			for (WorkPlace workPlace1 : workPlace) {
				if (workPlace1.getStatus().getStatusName() == null
						|| workPlace1.getStatus().getStatusName().equals("Active")
						|| workPlace1.getStatus().getStatusName().equals("InActive")
						|| workPlace1.getStatus().getStatusName().equals("Pending")) {
					WorkPlaceResponseEntity workPlaceData = new WorkPlaceResponseEntity(workPlace1);
					User assignedFromUsr = new User(workPlaceData.getAssignedFromUser());
					workPlaceData.setAssignedFromUser(assignedFromUsr);
					User assignedToUsr = new User(workPlaceData.getAssignedToUser());
					workPlaceData.setAssignedToUser(assignedToUsr);
					Status status = new Status(workPlaceData.getStatus());
					workPlaceData.setStatus(status);
					List<Address> addresses = new ArrayList<Address>();
					for (Address a : workPlaceData.getAddress()) {
						Address address = new Address(a);
						if (address.getWorkersCount() != null) {
							List<WorkersCount> workersCount = new ArrayList<WorkersCount>();
							for (WorkersCount wc : address.getWorkersCount()) {
								WorkersCount workerCount = new WorkersCount(wc);
								workersCount.add(workerCount);
							}

							address.setWorkersCount(workersCount);
						}

						addresses.add(address);
					}
					workPlaceData.setAddress(addresses);
					workPlaceResponseEntity1.add(workPlaceData);
				}
			}
			if (workPlaceResponseEntity1.isEmpty()) {
				WorkPlaceResponseEntity userResEntity = new WorkPlaceResponseEntity();
				userResEntity.setErrorCode(204);
				userResEntity.setMessage("No Data is Available");
				return new ResponseEntity(userResEntity, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(workPlaceResponseEntity1, HttpStatus.OK);
			}
		}
	}

	@DeleteMapping("/work-place/{workPlaceId}")
	public ResponseEntity deleteWorkPlace(@PathVariable("workPlaceId") Long workPlaceId) {
		Optional<WorkPlace> workPlace = workPlaceService.findById(workPlaceId);
		if (workPlace.isPresent()) {
			WorkPlace workPlaceData = workPlaceService.deleteWorkPlace(workPlaceId);
			if (workPlaceData == null) {
				WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
				workPlaceResponseEntity.setErrorCode(400);
				workPlaceResponseEntity.setMessage("Unable to delete WorkPlace");
				return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
			} else {
				WorkPlaceResponseEntity workPlaceResEntity = new WorkPlaceResponseEntity();
				workPlaceResEntity.setStatusCode(200);
				workPlaceResEntity.setDescription("WorkPlace Deleted Successfully");
				return new ResponseEntity(workPlaceResEntity, HttpStatus.OK);
			}
		} else {
			WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
			workPlaceResponseEntity.setErrorCode(404);
			workPlaceResponseEntity.setMessage("WorkPlace Not Found");
			return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/work-place/{address}/{date}/{userId}")
	public ResponseEntity deleteWorkPlaceComparingWithAddressAndDateInAttendance(
			@PathVariable("address") String address, @PathVariable("date") Date date, @PathVariable("userId") Long userId) {
		User user = new User(userId, userId);
		List<WorkPlace> workPlaceList = workPlaceService.findAllByAssignedToUser(user);
		if (workPlaceList.isEmpty()) {
			System.out.println("if");
			AttendanceResEntity userDataResEntity = new AttendanceResEntity();
			userDataResEntity.setErrorCode(400);
			userDataResEntity.setMessage("WorkPlace Not Assigned");
			return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
		} else {
			for (WorkPlace wp : workPlaceList) {
				if (wp.getFromDate().compareTo(date)
						* date.compareTo(wp.getToDate()) >= 0) {
					System.out.println("if");
					for (Address add : wp.getAddress()) {
						if(add.getAddressName().equals(address)) {
							Address addressDeleted = addressService.deleteAddress(add.getAddressId());
							if (addressDeleted == null) {
								WorkPlaceResponseEntity workPlaceResponseEntity = new WorkPlaceResponseEntity();
								workPlaceResponseEntity.setErrorCode(400);
								workPlaceResponseEntity.setMessage("Unable to delete WorkPlace for the given Address");
								return new ResponseEntity(workPlaceResponseEntity, HttpStatus.CONFLICT);
							} else {
								WorkPlaceResponseEntity workPlaceResEntity = new WorkPlaceResponseEntity();
								workPlaceResEntity.setStatusCode(200);
								workPlaceResEntity.setDescription("WorkPlace Deleted Successfully for the given Address");
								return new ResponseEntity(workPlaceResEntity, HttpStatus.OK);
							}
						}
					}
				} 
			}
			AttendanceResEntity userDataResEntity = new AttendanceResEntity();
			userDataResEntity.setErrorCode(400);
			userDataResEntity.setMessage("WorkPlace Not Assigned for the given Address");
			return new ResponseEntity(userDataResEntity, HttpStatus.CONFLICT);
		}
//		return null;
	}
}
