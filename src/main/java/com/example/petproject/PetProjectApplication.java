package com.example.petproject;

import com.example.petproject.exceptions.RoleNotFoundException;
import com.example.petproject.model.ERole;
import com.example.petproject.model.Role;
import com.example.petproject.model.User;
import com.example.petproject.repository.RoleRepository;
import com.example.petproject.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PetProjectApplication {
    @Resource
    UserRepository userRepository;

    @Resource
    RoleRepository roleRepository;

    @Resource
    PasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PetProjectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RoleNotFoundException("Role is not found.")));
        adminRoles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RoleNotFoundException("Role is not found.")));

        User adminUser = new User();
        adminUser.setRoles(adminRoles);
        adminUser.setName("admin");
        adminUser.setUsername("testAdmin2");
        adminUser.setPassword(bCryptPasswordEncoder.encode("admin"));

        userRepository.save(adminUser);
    }
}
