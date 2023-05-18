package com.example.petproject.controller;

import com.example.petproject.dto.request.modify.UserRequest;
import com.example.petproject.dto.response.UserResponse;
import com.example.petproject.facade.user.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PutMapping("/{id}")
    public UserResponse update(@Valid @RequestBody UserRequest request, @PathVariable long id) {
        return userFacade.updateUser(request, id);
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable long id) {
        return userFacade.getUser(id);
    }

//    @PostMapping
//    public ResponseUserDto create(@Valid @RequestBody CreateUserDto request) {
//        return userFacade.createUser(request);
//    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable long id) {
        return userFacade.deleteUser(id);
    }

    @GetMapping(params = {"page", "size"})
    public List<UserResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return userFacade.getUsers(page, size);
    }
}
