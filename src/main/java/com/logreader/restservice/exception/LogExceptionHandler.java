package com.logreader.restservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class LogExceptionHandler extends ResponseEntityExceptionHandler {

    private String INVALID_REQUEST = "INVALID_REQUEST";

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<ExceptionResponse> exception
            (InvalidInputException ex, WebRequest request)
    {
        System.out.println("Inside handleInvalidInputException");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionResponse error = new ExceptionResponse(INVALID_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
