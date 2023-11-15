package com.example.petproject.service.role;

import com.example.petproject.domain.Role;
import com.example.petproject.exception.ObjectNotFoundException;
import com.example.petproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(()-> new ObjectNotFoundException(name));
    }
}
