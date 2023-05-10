package com.example.petproject.controller;

import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.dto.response.MessageResponse;
import com.example.petproject.dto.response.UserInfoResponse;
import com.example.petproject.exception.RoleNotFoundException;
import com.example.petproject.model.ERole;
import com.example.petproject.model.Role;
import com.example.petproject.model.User;
import com.example.petproject.model.UserPrincipal;
import com.example.petproject.repository.RoleRepository;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.validator.SignupRequestValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //TODO добавити пласт сервісів
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final SignupRequestValidator signupRequestValidator;

    @InitBinder("signupRequest")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(signupRequestValidator);
    }

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder,
                          SignupRequestValidator signupRequestValidator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.signupRequestValidator = signupRequestValidator;
    }

    @PostMapping("/signin")
    public UserInfoResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().orElseThrow(() -> new RoleNotFoundException("User don't have any role"));

        return new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getName(),
                role);
    }

    @PostMapping("/signup")
    public MessageResponse registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        User user = new User(signupRequest.getUsername(),
                signupRequest.getName(),
                encoder.encode(signupRequest.getPassword()));

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException("%s is not found.".formatted(ERole.ROLE_USER)));

        user.setRole(userRole);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }
}
