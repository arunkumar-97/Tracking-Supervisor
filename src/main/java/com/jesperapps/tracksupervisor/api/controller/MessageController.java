package com.jesperapps.tracksupervisor.api.controller;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.jesperapps.tracksupervisor.api.model.LiveLocation;
import com.jesperapps.tracksupervisor.api.websocketmodel.OutputLocationMessage;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MessageController {
	
	@SendTo("/liveLocation/{userId}")
    public OutputLocationMessage sendMessage(@DestinationVariable Long userId, LiveLocation location) {
        OutputLocationMessage response = new OutputLocationMessage();
		try {
        response.updateLocation(location.getLatitude(), location.getLongitude());
		}
		catch(Exception err) {
			response.setMessage(err.toString());
		}
		return response;
	}
}
