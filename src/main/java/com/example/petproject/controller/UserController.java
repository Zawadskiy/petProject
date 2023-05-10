package com.example.petproject.controller;

import com.example.petproject.dto.model.user.UserDto;
import com.example.petproject.facade.user.UserFacade;
import com.example.petproject.model.User;
import com.example.petproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    @PostMapping("/user/role")
    public UserDto updateUser(@Valid @RequestBody UserDto request) {
        return userFacade.updateUser(request);
    }
}
