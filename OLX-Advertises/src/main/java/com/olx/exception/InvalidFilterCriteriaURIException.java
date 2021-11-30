package com.olx.exception;

public class InvalidFilterCriteriaURIException extends RuntimeException {

	private String errMsg;
	
	public InvalidFilterCriteriaURIException() {
		
	}
	
	public InvalidFilterCriteriaURIException(String errMsg) {
		this.errMsg = errMsg;
	}
	
	@Override
	public String toString() {
		return errMsg;
	}
}
