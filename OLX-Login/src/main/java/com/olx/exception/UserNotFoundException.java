package com.olx.exception;

public class UserNotFoundException extends RuntimeException {

	
	
	public UserNotFoundException() {}
		
	@Override
	public String toString() {
		return "User not found.";
	}
}
