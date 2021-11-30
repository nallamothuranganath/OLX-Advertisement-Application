package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {InvalidCategoryIdException.class})
	public ResponseEntity<Object> handleInvalidCategoryIdException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {InvalidDateConditionException.class})
	public ResponseEntity<Object> handleInvalidDateConditionException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {InvalidDateConditionURIException.class})
	public ResponseEntity<Object> handleInvalidDateConditionURIExc(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = {InvalidAdvertiseIdException.class})
	public ResponseEntity<Object> handleInvalidAdvertiseIdException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {NoAdvertiseFoundException.class})
	public ResponseEntity<Object> handleNoAdvertiseFoundException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {InvalidFilterCriteriaURIException.class})
	public ResponseEntity<Object> handleInvalidFilterCriteriaURIException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {InvalidUserException.class})
	public ResponseEntity<Object> handleInvalidUserException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {InvalidStatusIdException.class})
	public ResponseEntity<Object> handleInvalidStatusIdException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler(value = {InvalidUserAdvertiseIdException.class})
	public ResponseEntity<Object> handleInvalidUserAdvertiseIdException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {InavlidDateRangeException.class})
	public ResponseEntity<Object> handleInavlidDateRangeException(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
