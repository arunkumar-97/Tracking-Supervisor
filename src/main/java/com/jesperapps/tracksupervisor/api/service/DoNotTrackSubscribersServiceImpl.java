package com.jesperapps.tracksupervisor.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.DoNotTrackSubscribers;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.DoNotSubscribersRepository;

@Service
public class DoNotTrackSubscribersServiceImpl implements DoNotTrackSubscribersService{

	
	@Autowired
	private DoNotSubscribersRepository doNotSubscribersRepository;

	@Override
	public DoNotTrackSubscribers findBySubscriptionId(String subscriptionId) {
		// TODO Auto-generated method stub
		return doNotSubscribersRepository.findBySubscriptionId(subscriptionId);
	}

	@Override
	public DoNotTrackSubscribers save(DoNotTrackSubscribers doNotTrackSubscribers) {
		// TODO Auto-generated method stub
		return doNotSubscribersRepository.save(doNotTrackSubscribers);
	}

	@Override
	public DoNotTrackSubscribers findByUserId(User requestUser) {
		// TODO Auto-generated method stub
		return doNotSubscribersRepository.findByUser_UserId(requestUser.getUserId());
	}
}
