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

import java.util.stream.Collectors;

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
    public UserInfoResponse addRoleForUser(String username, String roleName) {

        Role role = roleRepository.findByName(ERole.valueOf(roleName))
                .orElseThrow(() -> new RoleNotFoundException("%s is not found.".formatted(roleName)));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username %s doesn't exists".formatted(username)));

        user.getRoles().add(role);
        userRepository.save(user);

        return new UserInfoResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRoles()
                        .stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toSet()));
    }

    @Override
    @Transactional
    public UserInfoResponse deleteRoleForUser(String username, String roleName) {
        Role role = roleRepository.findByName(ERole.valueOf(roleName))
                .orElseThrow(() -> new RoleNotFoundException("%s is not found.".formatted(roleName)));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username %s doesn't exists".formatted(username)));

        user.getRoles().remove(role);
        userRepository.save(user);

        return new UserInfoResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRoles()
                        .stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toSet()));
    }
}
