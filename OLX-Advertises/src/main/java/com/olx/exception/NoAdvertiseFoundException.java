package com.olx.exception;

public class NoAdvertiseFoundException extends RuntimeException {

	private String errMsg;
	
	public NoAdvertiseFoundException() {}
	
	public NoAdvertiseFoundException(String errMsg) {
		
		this.errMsg = errMsg;
	}
	
	@Override
	public String toString() {
		
		return errMsg;
	} 
}
