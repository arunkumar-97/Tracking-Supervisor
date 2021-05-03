package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.State;
import com.jesperapps.tracksupervisor.api.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {

	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<State> findAll() {
		// TODO Auto-generated method stub
		return stateRepository.findAll();
	}

	@Override
	public List<State> findByCountry(Optional<Country> countryFromDb) {
		// TODO Auto-generated method stub
		return stateRepository.findByCountry(countryFromDb);
	}

	@Override
	public Optional<State> findById(Integer stateId) {
		// TODO Auto-generated method stub
		return stateRepository.findById(stateId);
	}

	
}
