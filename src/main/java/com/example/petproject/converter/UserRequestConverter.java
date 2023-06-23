package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserRequestConverter implements Converter<UserRequest, User> {

    private final PasswordEncoder encoder;

    @Autowired
    public UserRequestConverter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User convert(UserRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<User> convert(List<UserRequest> source) {
        return source.stream()
                .map(this::mapToUser)
                .toList();
    }

    private User mapToUser(UserRequest userRequest) {

        User user = new User();

        user.setName(user.getName());
        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());

        return user;
    }
}
