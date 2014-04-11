package com.socialchef.service.exceptions;

import java.util.List;


public class InvalidDataException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 8901411046506874115L;
    private List<String> errors;
    
    public InvalidDataException() {
	    super();
    }

    public InvalidDataException(String msg) {
        super(msg);
    }
    
    public InvalidDataException(List<String> errors) {
        super();
        this.errors = errors;
    }
    
    public List<String> getErrors() {
    	return this.errors;
    }
}
