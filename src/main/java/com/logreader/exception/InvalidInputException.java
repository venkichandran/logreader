package com.logreader.exception;

import com.logreader.exception.LogReaderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor.
     *
     * @param String message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
