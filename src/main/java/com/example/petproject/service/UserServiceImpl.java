package com.example.petproject.service;

import com.example.petproject.exception.RoleNotFoundException;
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
    public User updateUser(User update) {

        User user = userRepository.findByUsername(update.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username %s doesn't exists".formatted(update.getUsername())));

        if (update.getRole() != null) {
            Role newRole = roleRepository.findByName(update.getRole().getName())
                    .orElseThrow(() -> new RoleNotFoundException("%s is not found.".formatted(update.getRole().getName())));
            user.setRole(newRole);
        }

        if (update.getPassword() != null) {
            user.setPassword(update.getPassword());
        }

        if (update.getUsername() != null) {
            user.setUsername(update.getUsername());
        }

        if (update.getName() != null) {
            user.setName(update.getName());
        }

        userRepository.save(user);

        return user;
    }
}
