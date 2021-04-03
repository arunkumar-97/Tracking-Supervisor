package com.jesperapps.tracksupervisor.api.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
{

//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(new SocketTextHandler(), "/socket");
//	}

	@Override
	public void configureMessageBroker( final MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/ws");
//		WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
	}
	
	
	@Override
		public void registerStompEndpoints(StompEndpointRegistry registry) {
			// TODO Auto-generated method stub
//			WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
				registry.addEndpoint("/our-websocket").withSockJS();
	}
	
	
}