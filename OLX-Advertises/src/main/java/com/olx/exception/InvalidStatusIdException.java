package com.olx.exception;

public class InvalidStatusIdException extends RuntimeException {

	int statusId;
	
	public InvalidStatusIdException() {}
	
	public InvalidStatusIdException(int statusId) {
		
		this.statusId = statusId;
	}
	
	@Override
	public String toString() {
		
		return "Invalid status id: "+statusId;
	}
}
