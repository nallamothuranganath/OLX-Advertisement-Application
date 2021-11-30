package com.olx.exception;

public class InvalidUserAdvertiseIdException extends RuntimeException {

	String errMsg;
	
	public InvalidUserAdvertiseIdException() {
	
		errMsg = "Advertise is not assosiated with the logged in User.";
	}
	
	@Override
	public String toString() {
		return errMsg;
	}
}
