package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackRequestingEntity;
import com.jesperapps.tracksupervisor.api.message.DoNotTrakResponseEntity;
import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.User;

public interface DoNotTrackService {

	DoNotTrack findByUser_userIdAndFromDateAndToDate(Long userId, Date fromDate, Date toDate);

	void save(DoNotTrack doNotTrack);

	Iterable<DoNotTrack> findAll();

	DoNotTrack findByTrackId(Integer trackId);
	
	DoNotTrack save1(DoNotTrack doNotTrack);

	Iterable<DoNotTrack> findByUser(Optional<User> classFromDb);

//	Iterable<DoNotTrack> findByUserAndApprovalStatus(Optional<User> userFromDb, Integer approvalStatusId);

	Iterable<DoNotTrack> findByUserAndApprovalStatus(Optional<User> userFromDb, Optional<ApprovalStatus> approvalStatusFromDb);

	

}
