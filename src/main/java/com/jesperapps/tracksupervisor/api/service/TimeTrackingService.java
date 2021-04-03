package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.model.TimeTracking;

public interface TimeTrackingService {

	TimeTracking save(TimeTracking leavebalReq);

	Optional<TimeTracking> findById(Long timeTrackingId);

	TimeTracking deleteTimeTracking(Long id);

	List<TimeTracking> findAll();

}
