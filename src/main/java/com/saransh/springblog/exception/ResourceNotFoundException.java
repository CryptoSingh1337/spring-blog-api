package com.saransh.springblog.exception;

/**
 * Created by CryptoSingh1337 on 7/28/2021
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
