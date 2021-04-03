package com.jesperapps.tracksupervisor.api.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Address;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.WorkersCount;

public interface WorkersCountRepository extends JpaRepository<WorkersCount, Long>{

	WorkersCount findByNoOfWorkersAndDateAndAddressAndNoOfWorkersUpdatedByUser(int noOfWorkers, Date date, Address add,
			User noOfWorkersUpdatedBy);

}
