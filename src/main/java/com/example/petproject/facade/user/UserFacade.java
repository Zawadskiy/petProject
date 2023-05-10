package com.example.petproject.facade.user;

import com.example.petproject.dto.model.user.UserDto;

public interface UserFacade {

    UserDto updateUser(UserDto request);
}
