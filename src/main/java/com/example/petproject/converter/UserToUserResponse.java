package com.example.petproject.converter;

import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserToUserResponse implements Converter<User, UserResponse> {
    @Override
    public UserResponse convert(User source) {

        UserResponse userDto = new UserResponse();

        userDto.setId(source.getId());
        userDto.setName(source.getName());
        userDto.setUsername(source.getUsername());
        userDto.setRole(source.getRole());

        return userDto;
    }

    @Override
    public List<UserResponse> convert(List<User> source) {
        return null;
    }
}
