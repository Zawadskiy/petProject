package com.example.petproject.dto.request.modify;

import com.example.petproject.model.Role;

public class UserRequest {
    private String username;
    private String name;
    private Role role;
    private String password;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

}
