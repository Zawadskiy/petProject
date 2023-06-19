package com.example.petproject.exception;

public class DormitoryNotFoundException extends AppException {
    public DormitoryNotFoundException(long id) {
        super("Dormitory with id %d not found".formatted(id));
    }
}
