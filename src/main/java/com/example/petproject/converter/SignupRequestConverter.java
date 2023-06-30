package com.example.petproject.converter;

import com.example.petproject.domain.User;
import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.service.role.RoleService;
import com.example.petproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SignupRequestConverter implements ConverterEx<SignupRequest, User> {

    private final UserService userService;

    private final RoleService roleService;

    private final static String USER_ROLE = "USER_ROLE";

    @Autowired
    public SignupRequestConverter(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public User convert(SignupRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public User convert(SignupRequest source, long id) {

        User user = convert(source);
        user.setId(id);

        return user;
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
        user.setPassword(userService.encode(signupRequest.getPassword()));
        user.setRole(roleService.getByName(USER_ROLE));

        return user;
    }
}
