package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.TimeTracking;

public interface TimeTrackingRepository extends JpaRepository<TimeTracking, Long>{

}
