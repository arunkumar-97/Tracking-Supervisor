package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.message.LocationDetailsResponseEntity;
import com.jesperapps.tracksupervisor.api.model.LocationDetails;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.LocationDetailsRepository;

@Service
public class LocationDetailsImplService implements LocationDetailsService {

	
	@Autowired
	private LocationDetailsRepository locationDetailsRepository;
	@Override
	public LocationDetails save(LocationDetails doNotTrack) {
		// TODO Auto-generated method stub
		return locationDetailsRepository.save(doNotTrack);
	}
	@Override
	public LocationDetails findByOrganizationAndLatitudeAndLongitude(Organization organization, Double latitude,
			Double longitude) {
		// TODO Auto-generated method stub
		return locationDetailsRepository.findByOrganizationAndLatitudeAndLongitude(organization,latitude,longitude);
	}
	@Override
	public List<LocationDetails> findAll() {
		// TODO Auto-generated method stub
		return locationDetailsRepository.findAll();
	}
	@Override
	public List<LocationDetailsResponseEntity> findByOrganization(Organization orgFromDb) {
		// TODO Auto-generated method stub
		return locationDetailsRepository.findByOrganization(orgFromDb);
	}
	@Override
	public Optional<LocationDetails> findById(Integer locationId) {
		// TODO Auto-generated method stub
		return locationDetailsRepository.findById(locationId);
	}
	@Override
	public LocationDetails findByUserAndLatitudeAndLongitude(User user, Double latitude, Double longitude) {
		// TODO Auto-generated method stub
		return locationDetailsRepository.findByUserAndLatitudeAndLongitude(user,latitude,longitude);
	}
	@Override
	public List<LocationDetailsResponseEntity> findByUser(User orgFromDb) {
		// TODO Auto-generated method stub
		return locationDetailsRepository.findByUser(orgFromDb);
	}

}
