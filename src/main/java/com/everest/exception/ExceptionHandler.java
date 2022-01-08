package com.everest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(FlightsNotFound.class)
    public ResponseEntity<Object> errorMessage(FlightsNotFound f)
    {
        return new ResponseEntity<>(f.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(LimitExceed.class)
    public ResponseEntity<Object> errorMessage(LimitExceed f)
    {
        return new ResponseEntity<>(f.getMessage(), HttpStatus.NOT_FOUND);
    }
}