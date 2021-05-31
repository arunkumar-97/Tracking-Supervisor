package com.jesperapps.tracksupervisor.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;
import com.jesperapps.tracksupervisor.api.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;

//	@Override
//	public Address findByWorkPlaceAndAddressName(WorkPlace workPlaceData, String address) {
//		// TODO Auto-generated method stub
//		return addressRepository.findByWorkPlaceAndAddressName(workPlaceData, address);
//	}

	@Override
	public Address deleteAddress(Long addressId) {
		Optional<Address> address = addressRepository.findById(addressId);
		if (address.isPresent()) {
			Address dbAddress = address.get();
			Status status = new Status((long) 4);
			dbAddress.setStatus(status);
			return addressRepository.save(dbAddress);
		} else {
			return null;
		}
	}

}
