package com.olx.exception;

public class LogOutUserMongoException extends RuntimeException{

	String errMsg;
	public LogOutUserMongoException() {}
	
	public LogOutUserMongoException(String errMsg) {
		this.errMsg = errMsg;
	}
	
	@Override
	public String toString() {
		return errMsg;
	}
}
