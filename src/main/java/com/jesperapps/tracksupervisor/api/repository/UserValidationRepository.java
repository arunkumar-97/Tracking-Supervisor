package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.User;

public interface UserValidationRepository extends JpaRepository<User, Long>{

	User findAllByPasscode(Long passcode);

}
