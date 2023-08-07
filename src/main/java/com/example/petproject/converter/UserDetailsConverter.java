package com.example.petproject.converter;

import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.exception.ObjectNotFoundException;
import com.example.petproject.service.role.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserDetailsConverter implements Converter<UserDetails, UserResponse> {

    private final RoleService roleService;

    public UserDetailsConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserResponse convert(UserDetails source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<UserResponse> convert(List<UserDetails> source) {
        return source.stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    private UserResponse mapToUserResponse(UserDetails userDetails) {

        UserResponse userDto = new UserResponse();

        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().orElseThrow(() -> new ObjectNotFoundException("User don't have any role"));

        userDto.setUsername(userDetails.getUsername());
        userDto.setRole(roleService.getByName(role));

        return userDto;
    }
}
