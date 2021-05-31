package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.DoNotTrackSubscribers;
import com.jesperapps.tracksupervisor.api.model.User;

public interface DoNotSubscribersRepository extends JpaRepository<DoNotTrackSubscribers, Integer> {

	DoNotTrackSubscribers findBySubscriptionId(String subscriptionId);


	DoNotTrackSubscribers findByUser_UserId(Long userId);

}
