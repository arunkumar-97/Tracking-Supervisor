package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.TimeTracking;
import com.jesperapps.tracksupervisor.api.repository.TimeTrackingRepository;

@Service
public class TimeTrackingServiceImpl implements TimeTrackingService{
	
	@Autowired
	private TimeTrackingRepository timeTrackingRepository;

	@Override
	public TimeTracking save(TimeTracking timeTracking) {
		// TODO Auto-generated method stub
		return timeTrackingRepository.save(timeTracking);
	}

	@Override
	public Optional<TimeTracking> findById(Long timeTrackingId) {
		// TODO Auto-generated method stub
		return timeTrackingRepository.findById(timeTrackingId);
	}

	@Override
	public TimeTracking deleteTimeTracking(Long id) {
		Optional<TimeTracking> timeTracking = timeTrackingRepository.findById(id.longValue());
		if (timeTracking.isPresent()) {
			TimeTracking dbTimeTracking = timeTracking.get();
			dbTimeTracking.setStatus("Deleted");
			return timeTrackingRepository.save(dbTimeTracking);
		} else {
			return null;
		}
	}

	@Override
	public List<TimeTracking> findAll() {
		// TODO Auto-generated method stub
		return timeTrackingRepository.findAll();
	}

}
