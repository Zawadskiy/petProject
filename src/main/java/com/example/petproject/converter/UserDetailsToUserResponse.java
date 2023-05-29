package com.example.petproject.converter;

import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.exception.RoleNotFoundException;
import com.example.petproject.service.role.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsToUserResponse implements Converter<UserDetails, UserResponse> {

    private final RoleService roleService;

    public UserDetailsToUserResponse(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserResponse convert(UserDetails source) {

        UserResponse userDto = new UserResponse();

        String role = source.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().orElseThrow(() -> new RoleNotFoundException("User don't have any role"));

        userDto.setUsername(source.getUsername());
        userDto.setRole(roleService.getByName(role));

        return userDto;
    }

    @Override
    public List<UserResponse> convert(List<UserDetails> source) {
        return null;
    }
}
