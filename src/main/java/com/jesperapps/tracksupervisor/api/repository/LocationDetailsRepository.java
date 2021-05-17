package com.jesperapps.tracksupervisor.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.User;

public interface LocationDetailsRepository extends JpaRepository<LocationDetails, Integer> {

	LocationDetails findByOrganizationAndLatitudeAndLongitude(Organization organization, Double latitude,
			Double longitude);

	List<LocationDetailsResponseEntity> findByOrganization(Organization orgFromDb);

	LocationDetails findByUserAndLatitudeAndLongitude(User user, Double latitude, Double longitude);

	List<LocationDetailsResponseEntity> findByUser(User orgFromDb);

}
