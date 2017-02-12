package com.github.burcakulug.samples.bb1.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidItemIdException extends RuntimeException {
    public InvalidItemIdException() {
    }

    public InvalidItemIdException(String message) {
        super(message);
    }

    public InvalidItemIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidItemIdException(Throwable cause) {
        super(cause);
    }

    public InvalidItemIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
