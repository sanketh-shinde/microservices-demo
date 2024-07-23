package com.eidiko.customer.exception;

public class UnknownException extends RuntimeException {

    public UnknownException() {
    }

    public UnknownException(String message) {
        super(message);
    }
}
