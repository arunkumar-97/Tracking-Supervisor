package com.jesperapps.tracksupervisor.api.service;

import java.util.List;

import com.jesperapps.tracksupervisor.api.model.SecondaryUser;
import com.jesperapps.tracksupervisor.api.model.User;

public interface SecondaryUserService {

	SecondaryUser save(SecondaryUser secondaryUser);

	List<SecondaryUser> findAllByPrimaryUser(User user);

}
