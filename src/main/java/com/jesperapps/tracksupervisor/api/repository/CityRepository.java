package com.jesperapps.tracksupervisor.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.City;
import com.jesperapps.tracksupervisor.api.model.State;

public interface CityRepository extends JpaRepository<City, Integer>{

	List<City> findAll();

	List<City> findByState(Optional<State> stateFromDb);

}
