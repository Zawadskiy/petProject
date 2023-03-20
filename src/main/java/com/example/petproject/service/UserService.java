package com.example.petproject.service;


import com.example.petproject.dto.response.UserInfoResponse;

public interface UserService {
    UserInfoResponse addRoleForUser(String username, String roleName);

    UserInfoResponse deleteRoleForUser(String username, String roleName);
}
