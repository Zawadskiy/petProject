package com.example.petproject.facade.user;

import com.example.petproject.dto.model.user.UserDto;

import java.util.List;

public interface UserFacade {
    UserDto updateUser(UserDto request);
    UserDto getUser(long id);
    UserDto createUser(UserDto request);
    UserDto deleteUser(long id);
    List<UserDto> getUsers(int page, int size);
}
