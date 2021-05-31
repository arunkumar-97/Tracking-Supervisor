package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.message.BackGroundRunResEntity;
import com.jesperapps.tracksupervisor.api.model.BackgroundRun;

public interface BackGroundRunService {

	BackgroundRun save(BackgroundRun backGroundRun);

	Optional<BackgroundRun> findById(Integer backgroundrunId);

	List<BackgroundRun> findAll();

}
