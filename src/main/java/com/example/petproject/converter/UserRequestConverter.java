package com.example.petproject.converter;

import com.example.petproject.domain.User;
import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserRequestConverter implements ConverterEx<UserRequest, User> {
    private final UserService userService;

    @Autowired
    public UserRequestConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User convert(UserRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public User convert(UserRequest source, long id) {

        User user = convert(source);
        user.setId(id);

        return user;
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
        user.setPassword(userService.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());

        return user;
    }
}
