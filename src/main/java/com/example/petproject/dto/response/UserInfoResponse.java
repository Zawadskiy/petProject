package com.example.petproject.dto.response;

import java.util.Set;

public class UserInfoResponse {

    private long id;
    private String username;
    private String name;
    private final Set<String> roles;

    public UserInfoResponse(long id, String username, String name, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Set<String> getRoles() {
        return roles;
    }
}
