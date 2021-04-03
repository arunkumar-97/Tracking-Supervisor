package com.jesperapps.tracksupervisor.api.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {



}
