package com.workintech.s19d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleApiException(ApiException apiException) {
        log.error("api exception occured: ", apiException.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(apiException.getHttpStatus().value(), apiException.getMessage());
        return new ResponseEntity<>(exceptionResponse, apiException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        log.error("unhandled exception occured: ", exception.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
