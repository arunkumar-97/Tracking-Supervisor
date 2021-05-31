package com.jesperapps.tracksupervisor.api.service;

import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;

public interface AddressService {

//	Address findByWorkPlaceAndAddressName(WorkPlace workPlaceData, String address);

	Address deleteAddress(Long addressId);

}
