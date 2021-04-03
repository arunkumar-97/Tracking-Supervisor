package com.jesperapps.tracksupervisor.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.repository.SecondaryUserRepository;

@Service
public class SecondaryUserImplementationService implements SecondaryUserService{

	
	@Autowired
	private SecondaryUserRepository secondaryUserRepository;

	@Override
	public SecondaryUser save(SecondaryUser secondaryUser) {
		// TODO Auto-generated method stub
		return secondaryUserRepository.save(secondaryUser);
	}
}
