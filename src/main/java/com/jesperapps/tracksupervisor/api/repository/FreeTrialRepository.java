package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.FreeTrial;

public interface FreeTrialRepository  extends JpaRepository<FreeTrial, Integer>{

	FreeTrial findByNoOfDays(Integer noOfDays);

}
