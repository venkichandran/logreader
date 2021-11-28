package com.logreader.exception;

import com.logreader.exception.LogReaderException;

public class InvalidInputException extends LogReaderException {

    /**
     * Exception constructor.
     *
     * @param String message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
