package com.jesperapps.tracksupervisor.api.service;

import java.util.List;
import java.util.Optional;

import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;

public interface UserService {

	Optional<User> createUser(User user);

	User updatePassCode(User user);

	Optional<User> findById(Long userId);

	User save(User users);

	List<User> findAll();

	User deleteUser(Long userId);

	List<User> findAllByPhoneNumber(String phoneNumber);

	List<User> findAllByPhoneNumberOrAlternatePhoneNumber(String phoneNumber, String phoneNumber2);

	User findByUserStatus(String string);

	User findByUserStatusAndUserId(String string, Long userId);

	List<User> findAllByAlternatePhoneNumberOrPhoneNumber(String phoneNumber, String alternatePhoneNumber);

	List<User> findAllByOrganization(Organization organization);

	List<User> findEmployeeByUserTypeAndOrganization(UserType users, Organization organization);

	User findUserByEmail(String userEmail);
	
	List<User> findAllByEmail(String email);

	Optional<User> findByPhoneNumber(String phoneNumber);

	void sendSms(String string, String phone);

	User findByEmailAndPassword(String email, String password);

	boolean checkPasswordIsSame(String password, String password2);

	User findUserByEmailOrPhoneNumber(String email, String phoneNumber);

	List<User> findAllByUserId(Integer userId);

}
