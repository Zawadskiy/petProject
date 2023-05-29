package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.model.User;
import com.example.petproject.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final Converter<UserRequest, User> userConverter;
    private final Converter<User, UserResponse> responseConverter;

    @Autowired
    public UserController(UserService userService,
                                Converter<UserRequest, User> userConverter,
                                Converter<User, UserResponse> responseConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.responseConverter = responseConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest request, @PathVariable long id) {

        User convert = userConverter.convert(request);
        convert.setId(id);

        User update = userService.update(convert);

        return new ResponseEntity<>(responseConverter.convert(update), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable long id) {

        User user = userService.getUser(id);

        return new ResponseEntity<>(responseConverter.convert(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {

        User convert = userConverter.convert(request);

        User user = userService.create(convert);

        return new ResponseEntity<>(responseConverter.convert(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        userService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<UserResponse>> getAll(@RequestParam int page, @RequestParam int size) {

        List<User> users = userService.getUsers(page, size);

        return new ResponseEntity<>(responseConverter.convert(users), HttpStatus.OK);
    }
}
