package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.SecondaryUser;

public interface SecondaryUserRepository extends JpaRepository<SecondaryUser, Integer>{

}
