package com.example.petproject.facade.user;

import com.example.petproject.converter.UserConverter;
import com.example.petproject.dto.model.user.UserDto;
import com.example.petproject.model.User;
import com.example.petproject.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserFacadeImpl(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User user = userService.updateUser(userConverter.toUser(userDto));

        return userConverter.toUserDto(user);
    }
}
