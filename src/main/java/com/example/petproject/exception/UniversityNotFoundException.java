package com.example.petproject.exception;

public class UniversityNotFoundException extends AppException {
    public UniversityNotFoundException(long id) {
        super("University with id %d not found".formatted(id));
    }
}
