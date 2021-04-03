package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;

public interface WorkPlaceService {

	WorkPlace save(WorkPlace workPlace);

	List<WorkPlace> findAllByAssignedFromUserAndAssignedToUser(User assignedFromUser, User assignedToUser);

	WorkPlace findByFromDateAndToDateIsBetweenAndAddress(Date fromDate, Date toDate, String addressName);

	List<WorkPlace> findAllByAssignedToUser(User assignedToUserId);

	WorkPlace findByFromDateIsBetween(Date fromDate, Date toDate);

	Optional<WorkPlace> findById(Long workPlaceId);

	WorkPlace deleteWorkPlace(Long workPlaceId);

}
