package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.jesperapps.tracksupervisor.api.message.OrganizataionWithUserRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationResponseEntity;
import com.jesperapps.tracksupervisor.api.model.Organization;

public interface OrganizationService {

	Organization save(Organization organization);

	Optional<Organization> findById(Integer organizationId);

	Organization deactivateOrganization(Integer organizationId);

	List<Organization> findAll();

	Organization checkOrganization(String organizationName);

	ResponseEntity createOrganization(OrganizataionWithUserRequestEntity orgRequest);

	Organization addOrganization(OrganizationRequestEntity organizationRequestEntity);
	

//	OrganizationDemo findByName(String organizationName);
//	
//	OrganizationDemo getOrganizationFromName(String organizationName);
//
//	OrganizationDemo addadmin(OrganizationWithProfilePictureRequestEntity organizationRequestWithProfilePicture);

}
