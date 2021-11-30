package com.olx.exception;

public class InvalidDateConditionException extends RuntimeException{
	
	private String errMsg;
	
	public InvalidDateConditionException() {}
	
	public InvalidDateConditionException(String errorMessage) {
		
		this.errMsg = errorMessage;
	}
	
	@Override
	public String toString() {
		
		return errMsg;
	}

}
