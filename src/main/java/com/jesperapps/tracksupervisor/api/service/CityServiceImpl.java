package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> findAll() {
		// TODO Auto-generated method stub
		return cityRepository.findAll();
	}

	@Override
	public List<City> findByState(Optional<State> stateFromDb) {
		// TODO Auto-generated method stub
		return cityRepository.findByState(stateFromDb);
	}

}
