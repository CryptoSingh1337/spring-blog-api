package com.saransh.springblog.web.controller;

import com.saransh.springblog.exception.PostIdMismatchException;
import com.saransh.springblog.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by CryptoSingh1337 on 7/28/2021
 */
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body(
                        e.getAllErrors()
                );
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<?> invalidRequestPropertyHandler() {
        return ResponseEntity.badRequest().body("Invalid Property values");
    }

    @ExceptionHandler(PostIdMismatchException.class)
    public ResponseEntity<?> postIdMismatchHandler() {
        return ResponseEntity.badRequest().body("Invalid Post Id");
    }
}
