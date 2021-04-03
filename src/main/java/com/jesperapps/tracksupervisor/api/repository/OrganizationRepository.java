package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	Organization findByOrganizationName(String organizationName);

}
