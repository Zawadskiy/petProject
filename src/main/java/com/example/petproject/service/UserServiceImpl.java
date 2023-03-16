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

import java.util.Optional;
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

        Optional<User> userOptional = userRepository.findByUsername(username);
        Optional<Role> byName = roleRepository.findByName(ERole.valueOf(roleName));

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User with username %s doesn't exists".formatted(username)));

        user.getRoles().add(byName.orElseThrow(() -> new RoleNotFoundException("%s is not found.".formatted(roleName))));

        userRepository.save(user);

        return new UserInfoResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRoles()
                        .stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toSet()));
    }
}
