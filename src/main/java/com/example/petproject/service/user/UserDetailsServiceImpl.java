package com.example.petproject.service.user;

import com.example.petproject.model.User;
import com.example.petproject.model.UserPrincipal;
import com.example.petproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        return UserPrincipal.build(user.orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
