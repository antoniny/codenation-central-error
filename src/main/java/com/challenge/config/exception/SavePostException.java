package com.challenge.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SavePostException extends RuntimeException {
    /**
	 *
	 */
	private static final long serialVersionUID = -3010322966541403710L;

	public SavePostException() {
        super();
    }

    public SavePostException(String message) {
        super(message);
    }
}
