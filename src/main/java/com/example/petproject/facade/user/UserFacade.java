package com.example.petproject.facade.user;

import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.dto.response.UserResponse;

import java.util.List;

public interface UserFacade {
    UserResponse updateUser(UserRequest request, long id);
    UserResponse getUser(long id);
    UserResponse createUser(UserRequest request);
    UserResponse deleteUser(long id);
    List<UserResponse> getUsers(int page, int size);
}
