package com.example.petproject.exceptions;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
