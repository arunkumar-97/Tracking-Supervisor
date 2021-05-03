package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.State;

public interface CityService {

	List<City> findAll();

	List<City> findByState(Optional<State> stateFromDb);

}
