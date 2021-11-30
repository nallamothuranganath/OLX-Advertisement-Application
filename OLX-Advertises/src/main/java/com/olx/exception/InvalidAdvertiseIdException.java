package com.olx.exception;

public class InvalidAdvertiseIdException extends RuntimeException {

	private int advertiseId;
	
	public InvalidAdvertiseIdException() {}
	
	public InvalidAdvertiseIdException(int advertiseId) {
		
		this.advertiseId = advertiseId;
	}
	
	@Override
	public String toString() {
		
		return "Advertise not found. Advertise Id: "+advertiseId;
	}
}
