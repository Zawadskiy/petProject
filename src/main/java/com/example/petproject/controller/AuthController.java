package com.example.petproject.controller;

import com.example.petproject.converter.UserConverter;
import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.dto.response.MessageResponse;
import com.example.petproject.dto.model.user.UserDto;
import com.example.petproject.model.ERole;
import com.example.petproject.model.Role;
import com.example.petproject.model.User;
import com.example.petproject.model.UserPrincipal;
import com.example.petproject.service.role.RoleService;
import com.example.petproject.service.user.UserService;
import com.example.petproject.validator.SignupRequestValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //TODO добавити пласт сервісів
    // TODO: 16.05.2023 Не советую инжекты вперемешку делать.
    //  Группируй по слою архитектуры/зоне ответтсвенности
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final SignupRequestValidator signupRequestValidator;
    private final UserConverter userConverter;
    private final RoleService roleService;

    @InitBinder("signupRequest")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(signupRequestValidator);
    }


    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          PasswordEncoder encoder,
                          SignupRequestValidator signupRequestValidator,
                          UserConverter userConverter,
                          RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
        this.signupRequestValidator = signupRequestValidator;
        this.userConverter = userConverter;
        this.roleService = roleService;
    }

    @PostMapping("/signin")
    // TODO: 16.05.2023 Работу с сессией я бы выпихнул в фильтр
    public UserDto authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
// TODO: 16.05.2023 Раздутый контроллер. Его задача - дергать сервисы, а не выполнять логику самостоятельно
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        return userConverter.toUserDto(userDetails);
    }

    @PostMapping("/signup")
    // TODO: 16.05.2023 что с названием метода?)
    public MessageResponse registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        User user = new User(signupRequest.getUsername(),
                signupRequest.getName(),
                encoder.encode(signupRequest.getPassword()));
// TODO: 16.05.2023 Опять же. Это должно быть в сервисе. В т.ч. и хеширование пароля
        Role userRole = roleService.findByName(ERole.ROLE_USER.name());

        user.setRole(userRole);
        userService.create(user);

        // TODO: 16.05.2023 Чем EntityResponse не угодил?
        return new MessageResponse("User registered successfully!");
    }
}
