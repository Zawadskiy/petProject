package com.example.petproject.service;


import com.example.petproject.dto.response.UserInfoResponse;
import com.example.petproject.model.ERole;

public interface UserService {
    UserInfoResponse modifyRoleForUser(String username, ERole roleName);
}
