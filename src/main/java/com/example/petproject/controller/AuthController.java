package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.domain.User;
import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.service.user.UserService;
import com.example.petproject.validator.SignupRequestValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final Converter<SignupRequest, User> signupConverter;

    private final SignupRequestValidator signupRequestValidator;

    @InitBinder("signupRequest")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(signupRequestValidator);
    }


    @Autowired
    public AuthController(UserService userService, Converter<SignupRequest, User> signupConverter, SignupRequestValidator signupRequestValidator) {
        this.userService = userService;
        this.signupConverter = signupConverter;
        this.signupRequestValidator = signupRequestValidator;
    }

    @PostMapping("/signin")
    // TODO: 28.07.2023 замечание то же. Вроде как не обязательно полноценный метод объявлять ради заглушки.
    public ResponseEntity<String> login() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequest signupRequest) {

        User user = signupConverter.convert(signupRequest);

        userService.create(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
