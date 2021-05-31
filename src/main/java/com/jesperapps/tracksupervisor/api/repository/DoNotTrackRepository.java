package com.jesperapps.tracksupervisor.api.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.User;

public interface DoNotTrackRepository extends JpaRepository<DoNotTrack, Integer>{

	DoNotTrack findByUser_userIdAndFromDateAndToDate(Long userId, Date fromDate, Date toDate);

	DoNotTrack findByTrackId(Integer trackId);

	Iterable<DoNotTrack> findByUser(Optional<User> classFromDb);

	Iterable<DoNotTrack> findByUserAndApprovalstatus(Optional<User> userFromDb, Optional<ApprovalStatus> approvalStatusFromDb);

	DoNotTrack findByUser_userId(Long userId);

	DoNotTrack findByUser_UserId(Long userId);
}
