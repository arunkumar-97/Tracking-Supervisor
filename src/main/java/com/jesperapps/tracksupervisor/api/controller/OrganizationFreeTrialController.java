package com.jesperapps.tracksupervisor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.service.OrganizationFreeTrialService;

@RestController
public class OrganizationFreeTrialController {

	
	@Autowired
	private OrganizationFreeTrialService organizationFreeTrialService;
}
