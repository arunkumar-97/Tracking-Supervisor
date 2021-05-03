package com.jesperapps.tracksupervisor.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.model.User;

public interface SecondaryUserRepository extends JpaRepository<SecondaryUser, Integer>{

	List<SecondaryUser> findAllByPrimaryUser(User user);

}
