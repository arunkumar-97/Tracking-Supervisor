package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;

import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkersCount;

public interface WorkersCountService {

	WorkersCount save(WorkersCount workersCount);

	WorkersCount findByNoOfWorkersAndDateAndAddressAndNoOfWorkersUpdatedByUser(int noOfWorkers, Date date, Address add,
			User noOfWorkersUpdatedBy);

}
