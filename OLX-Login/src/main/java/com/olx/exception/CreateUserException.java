package com.olx.exception;

public class CreateUserException extends RuntimeException {
	
	String errorMsg;

	public CreateUserException() {}
	
	public CreateUserException(String errorMsg) {
		
		this.errorMsg = "Custom Error: "+errorMsg;
	}
	
	@Override
	public String toString() {
		return errorMsg;
	}
}
