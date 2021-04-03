package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkersCount;
import com.jesperapps.tracksupervisor.api.repository.WorkersCountRepository;

@Service
public class WorkersCountServiceImpl implements WorkersCountService {

	@Autowired
	private WorkersCountRepository workersCountRepository;

	@Override
	public WorkersCount save(WorkersCount workersCount) {
		// TODO Auto-generated method stub
		return workersCountRepository.save(workersCount);
	}

	@Override
	public WorkersCount findByNoOfWorkersAndDateAndAddressAndNoOfWorkersUpdatedByUser(int noOfWorkers, Date date,
			Address add, User noOfWorkersUpdatedBy) {
		// TODO Auto-generated method stub
		return workersCountRepository.findByNoOfWorkersAndDateAndAddressAndNoOfWorkersUpdatedByUser(noOfWorkers, date, add,
				noOfWorkersUpdatedBy);
	}

}
