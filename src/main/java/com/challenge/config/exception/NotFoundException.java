package com.challenge.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3010322966541403790L;

	public NotFoundException() {
        super();
    }
    
    public NotFoundException(String message) {
        super(message);
    }
}
