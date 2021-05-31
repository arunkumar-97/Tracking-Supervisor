package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.BackgroundRun;

public interface BackGroundRepository extends JpaRepository<BackgroundRun, Integer>{

}
