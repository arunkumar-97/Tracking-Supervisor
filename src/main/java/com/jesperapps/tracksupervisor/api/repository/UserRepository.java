package com.jesperapps.tracksupervisor.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;

public interface UserRepository extends JpaRepository<User, Long>{

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

	User findByEmailAndPassword(String email, String password);

	User findUserByEmailOrPhoneNumber(String email, String phoneNumber);

	List<User> findAllByUserId(Integer userId);

	User findByOrganization(Organization organization);

	List<User> findAllByPhoneNumberOrEmail(String phoneNumber, String email);

	User findByUserId(Long userId);

	User findByEmailAndOtp(String email, String otp);
	
	
}
