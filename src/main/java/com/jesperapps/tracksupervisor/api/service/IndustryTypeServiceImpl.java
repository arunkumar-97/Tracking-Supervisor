package com.jesperapps.tracksupervisor.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.IndustryType;
import com.jesperapps.tracksupervisor.api.repository.IndustryTypeRepository;

@Service
public class IndustryTypeServiceImpl implements IndustryTypeService {

	
	@Autowired
	private IndustryTypeRepository industryTypeRepository;

	@Override
	public List<IndustryType> findAll() {
		// TODO Auto-generated method stub
		return industryTypeRepository.findAll();
	} 
}
