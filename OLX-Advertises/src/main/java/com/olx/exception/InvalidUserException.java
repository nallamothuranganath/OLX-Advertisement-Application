package com.olx.exception;

public class InvalidUserException extends RuntimeException{

	String userToken;
	
	public InvalidUserException() {}
	
	public InvalidUserException(String userToken) {
		
		this.userToken = userToken;
	}
	
	@Override
	public String toString() {
		return "Invalid user token: "+userToken;
	} 
}
