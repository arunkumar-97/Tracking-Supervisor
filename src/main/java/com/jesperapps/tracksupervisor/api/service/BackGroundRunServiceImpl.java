package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.message.BackGroundRunResEntity;
import com.jesperapps.tracksupervisor.api.model.BackgroundRun;
import com.jesperapps.tracksupervisor.api.repository.BackGroundRepository;

@Service
public class BackGroundRunServiceImpl implements BackGroundRunService {
	
	@Autowired
	private BackGroundRepository backGroundRepository;

	@Override
	public BackgroundRun save(BackgroundRun backGroundRun) {
		// TODO Auto-generated method stub
		return backGroundRepository.save(backGroundRun);
	}

	@Override
	public Optional<BackgroundRun> findById(Integer backgroundrunId) {
		// TODO Auto-generated method stub
		return backGroundRepository.findById(backgroundrunId);
	}

	@Override
	public List<BackgroundRun> findAll() {
		// TODO Auto-generated method stub
		return backGroundRepository.findAll();
	}

}
