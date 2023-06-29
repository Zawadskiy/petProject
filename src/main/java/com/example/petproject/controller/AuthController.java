package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.domain.User;
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
    // TODO: 22.06.2023 заглушка? вроде же можно сделать без явного определения в контроллере.
    //  Просто указать в секьюрити конфиге урл для логина
    public ResponseEntity<String> signIn() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequest signupRequest) {
// TODO: 29.06.2023 неудачное название переменной
        User convert = signupConverter.convert(signupRequest);

        userService.create(convert);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
