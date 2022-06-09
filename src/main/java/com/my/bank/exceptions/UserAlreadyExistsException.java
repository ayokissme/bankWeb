package com.my.bank.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
