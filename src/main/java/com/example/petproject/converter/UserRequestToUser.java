package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.domain.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserRequestToUser implements Converter<UserRequest, User> {

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
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());

        return user;
    }
}
