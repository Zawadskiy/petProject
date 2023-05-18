package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.exception.RoleNotFoundException;
import com.example.petproject.model.User;
import com.example.petproject.service.role.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final RoleService roleService;

    public UserConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    public User toUser(UserRequest userDto) {

        User user = new User();

        user.setName(user.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }

    public UserResponse toUserDto(User user) {

        UserResponse userDto = new UserResponse();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public UserResponse toUserDto(UserDetails user) {

        UserResponse userDto = new UserResponse();

        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().orElseThrow(() -> new RoleNotFoundException("User don't have any role"));

        userDto.setUsername(user.getUsername());
        userDto.setRole(roleService.getByName(role));

        return userDto;
    }
}
