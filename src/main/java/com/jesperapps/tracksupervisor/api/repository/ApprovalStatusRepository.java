package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.ApprovalStatus;

public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Integer>{

}
