package com.example.petproject.exception;

public class RoleNotFoundException extends RuntimeException {
    // TODO: 16.05.2023 мб стоит для узконаправленных исключений делать вшитое сообщение?
    //  хотя бы частично
    public RoleNotFoundException(String message) {
        super(message);
    }
}
