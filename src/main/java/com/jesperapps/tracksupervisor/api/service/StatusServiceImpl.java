package com.jesperapps.tracksupervisor.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Status;
import com.jesperapps.tracksupervisor.api.repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public List<Status> findAll() {
		// TODO Auto-generated method stub
		return statusRepository.findAll();
	}
}
