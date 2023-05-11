package com.example.petproject.controller;

import com.example.petproject.dto.model.user.UserDto;
import com.example.petproject.facade.user.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //TODO якщо роль не співпадаю з енамом, то ми отримаємо
    // "title": "Bad Request",
    // "status": 400
    // exception прокидуеться раніше на моменті десеріалізації...знайти варіант кастомізували відповідь сервера...
    @PutMapping("/update")
    public UserDto updateUser(@Valid @RequestBody UserDto request) {
        return userFacade.updateUser(request);
    }

    @GetMapping("/get/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userFacade.getUser(id);
    }

    @PostMapping("/new")
    public UserDto createUser(@Valid @RequestBody UserDto request) {
        return userFacade.createUser(request);
    }

    @DeleteMapping("/delete/{id}")
    public UserDto deleteUser(@PathVariable long id) {
        return userFacade.deleteUser(id);
    }

    @GetMapping(value = "/get/all", params = {"page", "size"})
    public List<UserDto> getUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userFacade.getUsers(page, size);
    }
}
