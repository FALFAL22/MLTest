package com.fl.ml.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MLException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MLException(String message, Throwable cause) {
        super(message, cause);
    }

    public MLException(String message) {
        super(message);
    }
}
