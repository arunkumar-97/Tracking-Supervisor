package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.extra.Response;
import com.jesperapps.tracksupervisor.api.message.DoNotTrackRequestingEntity;
import com.jesperapps.tracksupervisor.api.message.DoNotTrakResponseEntity;
import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;
import com.jesperapps.tracksupervisor.api.model.DoNotTrack;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.DoNotTrackRepository;

@Service
public class DoNotTrackServicelmpl implements DoNotTrackService {

	
	@Autowired
	private DoNotTrackRepository doNotTrackRepository;

	@Override
	public DoNotTrack findByUser_userIdAndFromDateAndToDate(Long userId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return doNotTrackRepository.findByUser_userIdAndFromDateAndToDate(userId,fromDate,toDate);
	}

	@Override
	public void save(DoNotTrack doNotTrack) {
		// TODO Auto-generated method stub
		doNotTrackRepository.save(doNotTrack);
	}

	@Override
	public Iterable<DoNotTrack> findAll() {
		// TODO Auto-generated method stub
		return doNotTrackRepository.findAll();
	}

	@Override
	public DoNotTrack findByTrackId(Integer trackId) {
		// TODO Auto-generated method stub
		return doNotTrackRepository.findByTrackId(trackId);
	}

	@Override
	public DoNotTrack save1(DoNotTrack doNotTrack) {
		// TODO Auto-generated method stub
		return doNotTrackRepository.save(doNotTrack);
	}

	@Override
	public Iterable<DoNotTrack> findByUser(Optional<User> classFromDb) {
		// TODO Auto-generated method stub
		return doNotTrackRepository.findByUser(classFromDb);
	}

	@Override
	public Iterable<DoNotTrack> findByUserAndApprovalStatus(Optional<User> userFromDb,
			Optional<ApprovalStatus> approvalStatusFromDb) {
		// TODO Auto-generated method stub
		return doNotTrackRepository.findByUserAndApprovalstatus(userFromDb,approvalStatusFromDb);
	}

	@Override
	public DoNotTrack findByUser_UserId(Long userId) {
		// TODO Auto-generated method stub
		return doNotTrackRepository.findByUser_UserId(userId);
	}

//	@Override
//	public User findByUserId(Long userId) {
//		// TODO Auto-generated method stub
//		DoNotTrack doNotTrackFromDB = this.doNotTrackRepository.findByUser_userId(userId);
//		return doNotTrackFromDB != null ? doNotTrackFromDB.getUser() : null;
//	}


	
}
