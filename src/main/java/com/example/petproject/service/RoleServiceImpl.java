package com.example.petproject.service;

import com.example.petproject.model.ERole;
import com.example.petproject.model.Role;
import com.example.petproject.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(ERole.valueOf(name)).orElseThrow(()-> new RuntimeException());
    }
}
