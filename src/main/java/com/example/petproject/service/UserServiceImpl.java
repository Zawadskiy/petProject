package com.example.petproject.service;

import com.example.petproject.dto.response.UserInfoResponse;
import com.example.petproject.exception.RoleNotFoundException;
import com.example.petproject.model.ERole;
import com.example.petproject.model.Role;
import com.example.petproject.model.User;
import com.example.petproject.repository.RoleRepository;
import com.example.petproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserInfoResponse modifyRoleForUser(String username, ERole role) {

        Role newRole = roleRepository.findByName(role)
                .orElseThrow(() -> new RoleNotFoundException("%s is not found.".formatted(role)));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username %s doesn't exists".formatted(username)));

        user.setRole(newRole);
        userRepository.save(user);

        return new UserInfoResponse(user.getId(), user.getUsername(), user.getName(), user.getRole().getName().name());
    }
}
