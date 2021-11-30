package com.olx.exception;

public class UserLoggedOutException extends RuntimeException{
	
	public UserLoggedOutException() {	
	}

	@Override
	public String toString() {
		return "User logged out. Please relogin again.";
	}
}
