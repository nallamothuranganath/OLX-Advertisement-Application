package com.olx.exception;

public class InvalidDateConditionURIException extends RuntimeException {

	private String errMsg;
	
	public InvalidDateConditionURIException() {}


	public InvalidDateConditionURIException(String errorMessage) {
		
		this.errMsg = errorMessage;
	}
	
	@Override
	public String toString() {

		return errMsg;
	}
}
