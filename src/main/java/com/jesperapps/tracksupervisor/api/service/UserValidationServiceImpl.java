package com.jesperapps.tracksupervisor.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.UserValidationRepository;

@Service
public class UserValidationServiceImpl implements UserValidationService{
	
	@Autowired
	private UserValidationRepository userValidationRepository;

	@Override
	public User findByPasscode(Long passcode) {
		return userValidationRepository.findAllByPasscode(passcode);
	}
}
