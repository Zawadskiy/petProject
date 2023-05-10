package com.example.petproject.dto.model.user;

public class UserDto {
    private String username;
    private String name;
    private String role;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String email) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }
}
