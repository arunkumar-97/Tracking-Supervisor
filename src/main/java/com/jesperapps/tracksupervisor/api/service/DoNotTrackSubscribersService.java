package com.jesperapps.tracksupervisor.api.service;

import com.jesperapps.tracksupervisor.api.model.DoNotTrackSubscribers;
import com.jesperapps.tracksupervisor.api.model.User;

public interface DoNotTrackSubscribersService {

	DoNotTrackSubscribers findBySubscriptionId(String subscriptionId);

	DoNotTrackSubscribers save(DoNotTrackSubscribers doNotTrackSubscribers);

	DoNotTrackSubscribers findByUserId(User requestUser);

}
