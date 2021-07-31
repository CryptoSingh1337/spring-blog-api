package com.saransh.springblog.exception;

/**
 * Created by CryptoSingh1337 on 7/30/2021
 */
public class IdMismatchException extends RuntimeException {

    public IdMismatchException() {
        super();
    }

    public IdMismatchException(String message) {
        super(message);
    }
}
