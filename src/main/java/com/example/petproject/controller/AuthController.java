package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.model.UserPrincipal;
import com.example.petproject.service.user.UserService;
import com.example.petproject.validator.SignupRequestValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // TODO: 16.05.2023 Не советую инжекты вперемешку делать.
    //  Группируй по слою архитектуры/зоне ответтсвенности
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final SignupRequestValidator signupRequestValidator;
    private final Converter<UserDetails, UserResponse> userConverter;

    @InitBinder("signupRequest")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(signupRequestValidator);
    }


    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          SignupRequestValidator signupRequestValidator,
                          Converter<UserDetails, UserResponse> userConverter) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.signupRequestValidator = signupRequestValidator;
        this.userConverter = userConverter;
    }

    @PostMapping("/signin")
    // TODO: 16.05.2023 Работу с сессией я бы выпихнул в фильтр
    public ResponseEntity<UserResponse> signIn(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
// TODO: 16.05.2023 Раздутый контроллер. Его задача - дергать сервисы, а не выполнять логику самостоятельно
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        return new ResponseEntity<>( userConverter.convert(userDetails), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequest signupRequest) {

        userService.create(signupRequest);

        return new ResponseEntity<>( "User registered successfully!", HttpStatus.OK);
    }
}
