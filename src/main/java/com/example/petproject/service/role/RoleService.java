package com.example.petproject.service.role;

import com.example.petproject.model.Role;

public interface RoleService {
    Role findByName(String name);
}