package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.message.FreeTrialResEntity;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;
import com.jesperapps.tracksupervisor.api.repository.FreeTrialRepository;

@Service
public class FreeTrialServiceImpl implements FreeTrialService{
	
	@Autowired
	private FreeTrialRepository freeTrialRepository;

	@Override
	public FreeTrial save(FreeTrial freeTrial) {
		// TODO Auto-generated method stub
		return freeTrialRepository.save(freeTrial);
	}

	@Override
	public FreeTrial findByNoOfDays(Integer noOfDays) {
		// TODO Auto-generated method stub
		return freeTrialRepository.findByNoOfDays(noOfDays);
	}

	@Override
	public List<FreeTrial> findAll() {
		// TODO Auto-generated method stub
		return freeTrialRepository.findAll();
	}

	@Override
	public Optional<FreeTrial> findById(Integer freetrialId) {
		// TODO Auto-generated method stub
		return freeTrialRepository.findById(freetrialId);
	}

}
