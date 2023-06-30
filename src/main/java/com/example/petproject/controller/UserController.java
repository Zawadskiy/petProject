package com.example.petproject.controller;

import com.example.petproject.converter.UserConverter;
import com.example.petproject.converter.UserRequestConverter;
import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.domain.User;
import com.example.petproject.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final UserRequestConverter userRequestConverter;
    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService,
                          UserRequestConverter userRequestConverter,
                          UserConverter userConverter) {
        this.userService = userService;
        this.userRequestConverter = userRequestConverter;
        this.userConverter = userConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest request, @PathVariable long id) {

        User user = userRequestConverter.convert(request, id);
        User updatedUser = userService.update(user);

        return new ResponseEntity<>(userConverter.convert(updatedUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable long id) {

        User user = userService.getUser(id);

        return new ResponseEntity<>(userConverter.convert(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {

        User convert = userRequestConverter.convert(request);

        User user = userService.create(convert);

        return new ResponseEntity<>(userConverter.convert(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        userService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAll(@PageableDefault Pageable pageable) {

        Page<User> users = userService.getAll(pageable);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
