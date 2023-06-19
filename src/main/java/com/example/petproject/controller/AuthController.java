package com.example.petproject.controller;

import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.service.user.UserService;
import com.example.petproject.validator.SignupRequestValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final SignupRequestValidator signupRequestValidator;

    @InitBinder("signupRequest")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(signupRequestValidator);
    }


    public AuthController(UserService userService, SignupRequestValidator signupRequestValidator) {
        this.userService = userService;
        this.signupRequestValidator = signupRequestValidator;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequest signupRequest) {

        userService.create(signupRequest);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
