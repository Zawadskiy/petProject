package com.example.petproject.dto.request.modify;

import com.example.petproject.domain.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private Role role;

    @NotNull
    @Size(min = 7)
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
