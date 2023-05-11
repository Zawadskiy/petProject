package com.example.petproject.facade.user;

import com.example.petproject.converter.UserConverter;
import com.example.petproject.dto.model.user.UserDto;
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
    public UserDto updateUser(UserDto userDto) {

        User user = userService.updateUser(userConverter.toUser(userDto));

        return userConverter.toUserDto(user);
    }

    @Override
    public UserDto getUser(long id) {

        User user = userService.findById(id);

        return userConverter.toUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = userService.create(userConverter.toUser(userDto));

        return userConverter.toUserDto(user);
    }

    @Override
    public UserDto deleteUser(long id) {

        userService.deleteById(id);

        return new UserDto();
    }

    @Override
    public List<UserDto> getUsers(int page, int size) {

        List<User> users = userService.getUsers(page, size);

        return users.stream().map(userConverter::toUserDto).toList();
    }
}
