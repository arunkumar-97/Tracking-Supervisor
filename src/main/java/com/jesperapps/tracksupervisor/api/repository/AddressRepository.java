package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.WorkPlace;

public interface AddressRepository extends JpaRepository<Address, Long>{

	Address findByWorkPlaceAndAddressName(WorkPlace workPlaceData, String address);

}
