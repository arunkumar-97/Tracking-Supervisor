package com.jesperapps.tracksupervisor.api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;

public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Long>{

	List<WorkPlace> findAllByAssignedFromUserAndAssignedToUser(User assignedFromUser, User assignedToUser);

	WorkPlace findByFromDateIsBetweenAndAddress(Date fromDate, Date toDate, String addressName);

	List<WorkPlace> findAllByAssignedToUser(User assignedToUserId);

	WorkPlace findByFromDateIsBetween(Date fromDate, Date toDate);

	WorkPlace findByAssignedToUserAndFromDateAndToDate(User assignedToUser, Date fromDate, Date toDate);

	List<WorkPlace> findAllByAssignedToUserAndFromDateAndToDate(User assignedToUser, Date fromDate, Date toDate);

}
