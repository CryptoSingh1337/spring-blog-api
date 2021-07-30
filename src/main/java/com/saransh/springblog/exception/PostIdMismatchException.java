package com.saransh.springblog.exception;

/**
 * Created by CryptoSingh1337 on 7/30/2021
 */
public class PostIdMismatchException extends RuntimeException {

    public PostIdMismatchException() {
        super();
    }

    public PostIdMismatchException(String message) {
        super(message);
    }
}
