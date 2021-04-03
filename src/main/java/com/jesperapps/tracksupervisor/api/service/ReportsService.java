package com.jesperapps.tracksupervisor.api.service;

import java.sql.Date;
import java.util.List;

import com.jesperapps.tracksupervisor.api.model.Reports;
import com.jesperapps.tracksupervisor.api.model.User;

public interface ReportsService {

	Reports save(Reports reports);

	List<Reports> findAllByUserAndDateBetween(User user, Date fromDate, Date toDate);

}
