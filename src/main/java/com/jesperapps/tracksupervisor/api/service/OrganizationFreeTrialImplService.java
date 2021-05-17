package com.jesperapps.tracksupervisor.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.OrganizationFreeTrial;
import com.jesperapps.tracksupervisor.api.repository.OrganizationFreeTrialRepository;

@Service
public class OrganizationFreeTrialImplService implements OrganizationFreeTrialService {
	
	
	@Autowired
	private OrganizationFreeTrialRepository organizationFreeTrialRepository;

	@Override
	public OrganizationFreeTrial save(OrganizationFreeTrial orgFreeTrial) {
		// TODO Auto-generated method stub
		return organizationFreeTrialRepository.save(orgFreeTrial);
	}

}
