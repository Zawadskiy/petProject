package com.example.petproject.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String obj, String id) {
        super("%s with id %s not found".formatted(obj, id));
    }
}
