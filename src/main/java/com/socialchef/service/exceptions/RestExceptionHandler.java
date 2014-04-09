package com.socialchef.service.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(value = { InvalidDataException.class })
    public ResponseEntity<Object> handleConflict(
	    final RuntimeException ex, final WebRequest request) {
	InvalidDataException invalid_ex = (InvalidDataException) ex;
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	
	return handleExceptionInternal(ex,
		invalid_ex.getErrors(),
        	headers,
        	HttpStatus.NOT_FOUND,
        	request);
    }
//    @ExceptionHandler(value = 
//	{ EntityNotFoundException.class, InvalidDataException.class })
//    protected ResponseEntity<Object> handleNotFound(
//	    final RuntimeException ex, final WebRequest request) {
//        return handleExceptionInternal(ex,
//        	ex.getMessage(),
//        	new HttpHeaders(),
//        	HttpStatus.NOT_FOUND,
//        	request);
//    }
}
