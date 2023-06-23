package com.example.petproject.converter;

import com.example.petproject.domain.User;
import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SignupRequestConverter implements Converter<SignupRequest, User> {

    private final PasswordEncoder encoder;

    private final RoleService roleService;

    @Autowired
    public SignupRequestConverter(PasswordEncoder encoder, RoleService roleService) {
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    public User convert(SignupRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<User> convert(List<SignupRequest> source) {
        return source.stream()
                .map(this::mapToUser)
                .toList();
    }

    private User mapToUser(SignupRequest signupRequest) {

        User user = new User();

        user.setName(user.getName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setRole(roleService.getByName("ROLE_USER"));

        return user;
    }
}
