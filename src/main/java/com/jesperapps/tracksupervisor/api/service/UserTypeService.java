package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jesperapps.tracksupervisor.api.model.UserType;

public interface UserTypeService {

	Optional<UserType> findById(Long utId);

	List<UserType> findAll();



}
