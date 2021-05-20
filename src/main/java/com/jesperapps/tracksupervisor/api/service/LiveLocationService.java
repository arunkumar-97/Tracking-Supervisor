package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.message.LiveLocationResEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.model.User;

public interface LiveLocationService {

	Optional<LiveLocation> findById(Integer liveLocationId);

	LiveLocation save(LiveLocation liveLocationReq);
	
	LiveLocation findByUserId(User requestedUser);

//	User findByUserId(Long userId);

	
//	List<LiveLocation> findByUser(User userFromDb);

//	List<LiveLocation> findByUser(LiveLocation userFromDb);

//	List<LiveLocation> findByUser(User userFromDb);

}
