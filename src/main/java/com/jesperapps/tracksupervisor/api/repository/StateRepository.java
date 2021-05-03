package com.jesperapps.tracksupervisor.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.model.State;

public interface StateRepository extends JpaRepository<State, Integer>{

	List<State> findAll();

	List<State> findByCountry(Optional<Country> countryFromDb);

}
