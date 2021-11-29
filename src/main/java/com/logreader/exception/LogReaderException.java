package com.logreader.exception;

public class LogReaderException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor.
     *
     * @param String message
     */
    public LogReaderException(String message) {
        super(message);
    }
}
