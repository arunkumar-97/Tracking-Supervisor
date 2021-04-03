package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.repository.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Override
	public Optional<UserType> findById(Long utId) {
		return userTypeRepository.findById(utId);
	}

	@Override
	public List<UserType> findAll() {
		return userTypeRepository.findAll();
	}

}
