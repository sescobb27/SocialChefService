package com.socialchef.service.exceptions;

import java.util.LinkedList;
import java.util.List;

public class SocialChefException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3811614576576127668L;
	
	private List<String> errors;
    
    public SocialChefException() {
	    super();
    }

    public SocialChefException(String msg) {
        super();
        this.errors = new LinkedList<String>();
        this.errors.add(msg);
    }
    
    public SocialChefException(List<String> errors) {
        super();
        this.errors = errors;
    }
    
    public List<String> getErrors() {
    	return this.errors;
    }
}
