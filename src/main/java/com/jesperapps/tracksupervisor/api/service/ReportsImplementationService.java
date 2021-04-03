package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Reports;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.repository.ReportsRepository;

@Service
public class ReportsImplementationService implements ReportsService{
	
	@Autowired
	private ReportsRepository reportsRepository;

	@Override
	public Reports save(Reports reports) {
		// TODO Auto-generated method stub
		return reportsRepository.save(reports);
	}

	@Override
	public List<Reports> findAllByUserAndDateBetween(User user, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return reportsRepository.findAllByUserAndDateBetween(user,fromDate,toDate);
	}

}
