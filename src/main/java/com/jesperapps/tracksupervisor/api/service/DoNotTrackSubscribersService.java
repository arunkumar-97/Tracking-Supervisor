package com.jesperapps.tracksupervisor.api.service;

import com.jesperapps.tracksupervisor.api.model.DoNotTrackSubscribers;

public interface DoNotTrackSubscribersService {

	DoNotTrackSubscribers findBySubscriptionId(String subscriptionId);

	DoNotTrackSubscribers save(DoNotTrackSubscribers doNotTrackSubscribers);

}
