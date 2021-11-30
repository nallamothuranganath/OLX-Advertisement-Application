package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidCategoryIdException extends RuntimeException{
	
	private int categoryId;
	
	public InvalidCategoryIdException() {
		//categoryId = 0;
	}
	
	public InvalidCategoryIdException(int value) {
	
		this.categoryId = value;
	}
	
	@Override
	public String toString() {
		return "Invalid Category Id: "+categoryId;
	}
}
