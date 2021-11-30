package com.olx.exception;

import java.time.LocalDate;

public class InavlidDateRangeException extends RuntimeException {

	String fromDate;
	String toDate;
	
	public InavlidDateRangeException() {}
	
	public InavlidDateRangeException(LocalDate fromDate, LocalDate toDate) {
		this.fromDate = fromDate.toString();
		this.toDate = toDate.toString();
	}
	
	@Override
	public String toString() {
		return "Invalid Date Range - From Date: "+ fromDate+" To Date: "+toDate;
	}
}
