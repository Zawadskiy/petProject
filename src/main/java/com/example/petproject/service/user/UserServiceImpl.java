package com.example.petproject.service.user;

import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.domain.Role;
import com.example.petproject.domain.User;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.service.role.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public User update(User update) {
        return userRepository.save(update);
    }

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    // TODO: 23.06.2023 фуфу. Либо везде тянем дто в слой сервисов, либо везде передаем домен/набор исходных параметров
    public User create(SignupRequest signupRequest) {

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getName(),
                encoder.encode(signupRequest.getPassword())
        );

        Role role = roleService.getByName("ROLE_USER");
        user.setRole(role);

        return create(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
