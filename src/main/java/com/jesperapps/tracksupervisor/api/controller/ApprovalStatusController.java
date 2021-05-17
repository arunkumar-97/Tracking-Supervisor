package com.jesperapps.tracksupervisor.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.ApprovalStatusResponseEntity;
import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;
import com.jesperapps.tracksupervisor.api.repository.ApprovalStatusRepository;
import com.jesperapps.tracksupervisor.api.utils.ApprovalsStatus;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ApprovalStatusController {

	
	@Autowired
	private ApprovalStatusRepository approvalStatusRepository;
	
	

	@PostMapping("/approvalstatus")
	public Response createUserType() {
		Response response=new Response(200,"ApprovalStatus Created Successfully") {
		};
		ApprovalStatus user1=new ApprovalStatus(1,ApprovalsStatus.PENDING);
		ApprovalStatus user2=new ApprovalStatus(2,ApprovalsStatus.ACCEPTED);
		ApprovalStatus user3=new ApprovalStatus(3,ApprovalsStatus.DENIED);
		ApprovalStatus user4=new ApprovalStatus(4,ApprovalsStatus.WITHDRAW);
		approvalStatusRepository.save(user1);
		approvalStatusRepository.save(user2);
		approvalStatusRepository.save(user3);
		approvalStatusRepository.save(user4);
		
	
		
		return response  ;
		
	}
	
	@GetMapping("/approvalstatus")
	public List<ApprovalStatusResponseEntity> getAllUserTypeDetails(){
		
		return approvalStatusRepository.findAll().stream().map(userType -> new ApprovalStatusResponseEntity(userType)).collect(Collectors.toList());

		
		
}

	public Optional<ApprovalStatus> findById(int approvalStatusId) {
		// TODO Auto-generated method stub
		return approvalStatusRepository.findById(approvalStatusId);
	}


	
}
