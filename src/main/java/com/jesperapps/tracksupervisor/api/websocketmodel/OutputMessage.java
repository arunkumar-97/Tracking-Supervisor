package com.jesperapps.tracksupervisor.api.websocketmodel;

public class OutputMessage {
	
	
	private String message;
	 
    public OutputMessage(String message){
        this.message = message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getMessage() {
        return message;
    }

}
