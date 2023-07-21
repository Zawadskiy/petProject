package com.example.petproject.handler;

import com.example.petproject.error.ApiError;
import com.example.petproject.exception.DuplicateUsernameException;
import com.example.petproject.exception.ObjectNotFoundException;
import com.example.petproject.exception.RoleNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseEntity<String> roleNotFound(RoleNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ResponseEntity<String> usernameIsTaken(DuplicateUsernameException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ResponseEntity<String> objectNotFound(ObjectNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {


        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> String.format("%s : %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        List<String> globalErrors = ex.getBindingResult().getGlobalErrors()
                .stream()
                .map(error -> String.format("%s : %s", error.getObjectName(), error.getDefaultMessage()))
                .toList();

        errors.addAll(globalErrors);

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }
}
