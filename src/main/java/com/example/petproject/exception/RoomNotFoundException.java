package com.example.petproject.exception;

public class RoomNotFoundException extends AppException {
    public RoomNotFoundException(long id) {
        super("Room with id %d not found".formatted(id));
    }
}
