package com.example.petproject.service;

import com.example.petproject.model.ERole;
import com.example.petproject.model.Role;

import java.util.Optional;

public interface RoleService {
    Role findByName(String name);
}
