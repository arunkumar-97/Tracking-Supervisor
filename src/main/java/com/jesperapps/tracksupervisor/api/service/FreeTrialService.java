package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.message.FreeTrialResEntity;
import com.jesperapps.tracksupervisor.api.model.FreeTrial;

public interface FreeTrialService {

	FreeTrial save(FreeTrial freeTrial);

	FreeTrial findByNoOfDays(Integer noOfDays);

	List<FreeTrial> findAll();

	Optional<FreeTrial> findById(Integer freetrialId);

}
