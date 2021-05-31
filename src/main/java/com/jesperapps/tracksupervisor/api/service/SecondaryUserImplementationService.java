package com.jesperapps.tracksupervisor.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.model.User;
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

	@Override
	public List<SecondaryUser> findAllByPrimaryUser(User user) {
		// TODO Auto-generated method stub
		return secondaryUserRepository.findAllByPrimaryUser(user);
	}

	@Override
	public SecondaryUser findBySecondaryUser(User user) {
		// TODO Auto-generated method stub
		return this.secondaryUserRepository.findBySecondaryUser_UserId(user.getUserId());
	}
}
