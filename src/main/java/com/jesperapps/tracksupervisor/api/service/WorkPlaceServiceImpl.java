package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;
import com.jesperapps.tracksupervisor.api.repository.WorkPlaceRepository;

@Service
public class WorkPlaceServiceImpl implements WorkPlaceService{

	@Autowired
	private WorkPlaceRepository workPlaceRepository;

	@Override
	public WorkPlace save(WorkPlace workPlace) {
		return workPlaceRepository.save(workPlace);
	}

	@Override
	public List<WorkPlace> findAllByAssignedFromUserAndAssignedToUser(User assignedFromUser, User assignedToUser) {
		// TODO Auto-generated method stub
		return workPlaceRepository.findAllByAssignedFromUserAndAssignedToUser(assignedFromUser, assignedToUser);
	}

//	@Override
//	public WorkPlace findByFromDateAndToDateIsBetweenAndAddress(Date fromDate, Date toDate, String addressName) {
//		// TODO Auto-generated method stub
//		return workPlaceRepository.findByFromDateIsBetweenAndAddress(fromDate, toDate, addressName);
//	}

	@Override
	public List<WorkPlace> findAllByAssignedToUser(User assignedToUserId) {
		// TODO Auto-generated method stub
		return workPlaceRepository.findAllByAssignedToUser(assignedToUserId);
	}

	@Override
	public WorkPlace findByFromDateIsBetween(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return workPlaceRepository.findByFromDateIsBetween(fromDate, toDate);
	}

	@Override
	public Optional<WorkPlace> findById(Long workPlaceId) {
		// TODO Auto-generated method stub
		return workPlaceRepository.findById(workPlaceId);
	}

	@Override
	public WorkPlace deleteWorkPlace(Long workPlaceId) {
		Optional<WorkPlace> workPlace = workPlaceRepository.findById(workPlaceId);
		if (workPlace.isPresent()) {
			WorkPlace dbWorkPlace = workPlace.get();
			dbWorkPlace.getStatus().setStatusName("Deleted");
			return workPlaceRepository.save(dbWorkPlace);
		} else {
			return null;
		}
	}

	@Override
	public WorkPlace findByAssignedToUserAndFromDateAndToDate(User assignedToUser, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return workPlaceRepository.findByAssignedToUserAndFromDateAndToDate(assignedToUser,fromDate,toDate);
	}

	@Override
	public List<WorkPlace> findAllByAssignedToUserAndFromDateAndToDate(User assignedToUser, Date fromDate,
			Date toDate) {
		// TODO Auto-generated method stub
		return workPlaceRepository.findAllByAssignedToUserAndFromDateAndToDate(assignedToUser,fromDate,toDate);
	}

}
