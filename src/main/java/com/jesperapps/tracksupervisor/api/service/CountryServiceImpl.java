package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.message.CountryResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Country;
import com.jesperapps.tracksupervisor.api.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService{

	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> findAll() {
		// TODO Auto-generated method stub
		return countryRepository.findAll();
	}

	@Override
	public Optional<Country> findById(Integer countryId) {
		// TODO Auto-generated method stub
		return countryRepository.findById(countryId);
	}
}
