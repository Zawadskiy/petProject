package com.example.petproject.dto.request;

import com.example.petproject.validator.RoleNameValidation;
import jakarta.validation.constraints.NotBlank;

public class ModifyUserRoles {

    @NotBlank(message = "Can't be blank")
    private String username;

    @NotBlank(message = "Can't be blank")
    @RoleNameValidation
    private String roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
