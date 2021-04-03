package com.jesperapps.tracksupervisor.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.OtpSms;

public interface OtpSmsRepository extends JpaRepository<OtpSms, Long> {
	
	
	OtpSms deleteByPhoneNumber(String phone);

	List<OtpSms> findAllByPhoneNumber(String phone);

	Optional<OtpSms> findByPhoneNumber(String phoneNumber);

}
