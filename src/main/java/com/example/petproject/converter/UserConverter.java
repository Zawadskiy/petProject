package com.example.petproject.converter;

import com.example.petproject.dto.model.user.UserDto;
import com.example.petproject.exception.RoleNotFoundException;
import com.example.petproject.model.Role;
import com.example.petproject.model.User;
import com.example.petproject.service.role.RoleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

@Component
public class UserConverter {

    private final RoleService roleService;

    public UserConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    public User toUser(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(user.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        Role role = roleService.findByName(userDto.getRole());
        user.setRole(role);

        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().getName().name());

        return userDto;
    }

    public UserDto toUserDto(UserDetails user) {
        UserDto userDto = new UserDto();

        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().orElseThrow(() -> new RoleNotFoundException("User don't have any role"));

        userDto.setUsername(user.getUsername());
        userDto.setRole(role);

        return userDto;
    }
}
