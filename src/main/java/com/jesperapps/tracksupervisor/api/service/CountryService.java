package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.message.CountryResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Country;

public interface CountryService {

	List<Country> findAll();

	Optional<Country> findById(Integer countryId);

}
