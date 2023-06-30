package com.example.petproject.exception;

public class ObjectNotFoundException extends RuntimeException {
    public <T, ID> ObjectNotFoundException(Class<T> clazz, ID id) {
        super("%s with id %s not found".formatted(clazz.getName(), id));
    }
}
