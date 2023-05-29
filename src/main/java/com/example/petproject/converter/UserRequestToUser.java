package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRequestToUser implements Converter<UserRequest, User> {
    @Override
    public User convert(UserRequest source) {
        User user = new User();

        user.setName(user.getName());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setRole(source.getRole());

        return user;
    }

    @Override
    public List<User> convert(List<UserRequest> source) {
        return null;
    }
}
