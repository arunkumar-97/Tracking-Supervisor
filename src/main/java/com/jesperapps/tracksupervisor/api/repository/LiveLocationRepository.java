package com.jesperapps.tracksupervisor.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.message.LiveLocationResEntity;
import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.model.User;

public interface LiveLocationRepository extends JpaRepository<LiveLocation, Integer>{
	
	LiveLocation findByUser_UserId(Long userId);

	

	

	
}
