package com.example.petproject.exception;

public class StudentNotFoundException extends AppException {
    public StudentNotFoundException(long id) {
        super("Student with id %d not found".formatted(id));
    }
}
