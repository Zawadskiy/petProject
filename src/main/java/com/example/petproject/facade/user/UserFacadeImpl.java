package com.example.petproject.facade.user;

import com.example.petproject.converter.UserConverter;
import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.model.User;
import com.example.petproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public UserFacadeImpl(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @Override
    public UserResponse updateUser(UserRequest userDto, long id) {

        User user = userConverter.toUser(userDto);
        user.setId(id);
        userService.update(user);

        return userConverter.toUserDto(user);
    }

    @Override
    public UserResponse getUser(long id) {

        User user = userService.getUser(id);

        return userConverter.toUserDto(user);
    }

    @Override
    public UserResponse createUser(UserRequest userDto) {

        User user = userService.create(userConverter.toUser(userDto));

        return userConverter.toUserDto(user);
    }

    @Override
    public UserResponse deleteUser(long id) {

        userService.delete(id);

        return new UserResponse();
    }

    @Override
    public List<UserResponse> getUsers(int page, int size) {

        List<User> users = userService.getUsers(page, size);

        return users.stream().map(userConverter::toUserDto).toList();
    }
}
