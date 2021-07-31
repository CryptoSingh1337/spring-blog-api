package com.saransh.springblog.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saransh.springblog.exception.IdMismatchException;
import com.saransh.springblog.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CryptoSingh1337 on 7/28/2021
 */
@RequiredArgsConstructor
@ControllerAdvice
public class MvcExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationHandler(MethodArgumentNotValidException e) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getFieldErrors())
            errors.put(error.getField(), error.getDefaultMessage());
        return ResponseEntity.badRequest()
                .body(
                    objectMapper.writeValueAsString(errors)
                );
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<?> invalidRequestPropertyHandler() {
        return ResponseEntity.badRequest().body("Invalid Property values");
    }

    @ExceptionHandler(IdMismatchException.class)
    public ResponseEntity<?> idMismatchHandler() {
        return ResponseEntity.badRequest().body("Invalid Id");
    }
}
