package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.DoNotTrackSubscribers;

public interface DoNotSubscribersRepository extends JpaRepository<DoNotTrackSubscribers, Integer> {

	DoNotTrackSubscribers findBySubscriptionId(String subscriptionId);

}
