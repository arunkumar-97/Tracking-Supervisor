package com.jesperapps.tracksupervisor.api.websocket.config;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

	
	@MessageMapping("/mesages")
	@SendTo("/topic/messages")
	public ResponseMessage getMessage(final Message message)throws InterruptedException{
		
		Thread.sleep(1000);
		return new ResponseMessage(message.getMessageContent());
		
	}
}
