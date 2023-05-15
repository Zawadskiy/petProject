package com.example.petproject.exception;

// TODO: 16.05.2023 Это не дто. К слову, я бы еще общий класс аля AppException сделал бы
public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
