package com.epam.model;

import javax.ws.rs.core.Response.Status;

public class NewsPaperWSException extends RuntimeException{
	
	private String message;
	private Status status;
	
	public NewsPaperWSException(String message) {
		this.message = message;
	}
	public NewsPaperWSException(String message, Status status) {
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Status getStatus() {
		return status;
	}


}
