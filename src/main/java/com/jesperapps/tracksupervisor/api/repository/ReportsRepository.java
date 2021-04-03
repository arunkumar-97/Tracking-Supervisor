package com.jesperapps.tracksupervisor.api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Reports;
import com.jesperapps.tracksupervisor.api.model.User;

public interface ReportsRepository extends JpaRepository<Reports, Integer> {

	List<Reports> findAllByUserAndDateBetween(User user, Date fromDate, Date toDate);

}
