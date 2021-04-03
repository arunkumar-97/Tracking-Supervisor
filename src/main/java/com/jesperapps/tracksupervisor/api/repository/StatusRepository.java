package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

}
