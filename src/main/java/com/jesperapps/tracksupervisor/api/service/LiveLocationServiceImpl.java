package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.message.LiveLocationResEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.LiveLocationRepository;

@Service
public class LiveLocationServiceImpl implements LiveLocationService {
	
	
	@Autowired
	private LiveLocationRepository liveLocationRepository;

	@Override
	public Optional<LiveLocation> findById(Integer liveLocationId) {
		// TODO Auto-generated method stub
		return liveLocationRepository.findById(liveLocationId);
	}

	@Override
	public LiveLocation save(LiveLocation liveLocationReq) {
		// TODO Auto-generated method stub
		return liveLocationRepository.save(liveLocationReq);
	}

	@Override
	public LiveLocation findByUserId(User requestedUser) {
		// TODO Auto-generated method stub
		return this.liveLocationRepository.findByUser_UserId(requestedUser.getUserId());
	}

	@Override
	public List<LiveLocationResEntity> findByUser(User userFromDb) {
		// TODO Auto-generated method stub
		return liveLocationRepository.findByUser(userFromDb);
	}

	

	
	
	

}
