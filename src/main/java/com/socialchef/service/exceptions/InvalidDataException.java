package com.socialchef.service.exceptions;

public class InvalidDataException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 8901411046506874115L;
    
    public InvalidDataException() {
	    super();
    }

    public InvalidDataException(String msg) {
        super(msg);
    }
}
