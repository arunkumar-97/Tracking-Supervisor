package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.message.StateResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.State;

public interface StateService {

	List<State> findAll();

	List<State> findByCountry(Optional<Country> countryFromDb);

	Optional<State> findById(Integer stateId);

	

}
