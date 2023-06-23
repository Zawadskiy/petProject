package com.example.petproject.converter;

import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.domain.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserConverter implements Converter<User, UserResponse> {

    @Override
    public UserResponse convert(User source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<UserResponse> convert(List<User> source) {
        return source.stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    private UserResponse mapToUserResponse(User user) {

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());

        return userResponse;
    }
}
