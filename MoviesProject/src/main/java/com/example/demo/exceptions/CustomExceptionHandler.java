package com.example.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {CustomException.class})
	public ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request){
		String errorMessageDescription = ex.getLocalizedMessage();
		if(errorMessageDescription == null)
			errorMessageDescription= ex.toString();
		
		return new ResponseEntity<Object>(errorMessageDescription, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
	}

}
