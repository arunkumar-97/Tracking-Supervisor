package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.User;

public interface LocationDetailsService {

	LocationDetails save(LocationDetails doNotTrack);

	LocationDetails findByOrganizationAndLatitudeAndLongitude(Organization organization, Double latitude,
			Double longitude);

	List<LocationDetails> findAll();

	List<LocationDetailsResponseEntity> findByOrganization(Organization orgFromDb);

	Optional<LocationDetails> findById(Integer locationId);

	LocationDetails findByUserAndLatitudeAndLongitude(User user, Double latitude, Double longitude);

	List<LocationDetailsResponseEntity> findByUser(User orgFromDb);

}
