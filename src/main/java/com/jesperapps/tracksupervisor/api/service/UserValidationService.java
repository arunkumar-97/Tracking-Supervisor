package com.jesperapps.tracksupervisor.api.service;

import com.jesperapps.tracksupervisor.api.model.User;

public interface UserValidationService {

	User findByPasscode(Long passcode);

}
