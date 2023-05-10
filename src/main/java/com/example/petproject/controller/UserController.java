package com.example.petproject.controller;

import com.example.petproject.dto.request.ModifyUserRoleRequest;
import com.example.petproject.dto.response.UserInfoResponse;
import com.example.petproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO якщо роль не співпадаю з енамом, то ми отримаємо
    // "title": "Bad Request",
    // "status": 400
    // exception прокидуеться раніше на моменті десеріалізації...знайти варіант кастомізували відповідь сервера...
    @PostMapping("/user/role")
    public UserInfoResponse modifyRoleForUser(@Valid @RequestBody ModifyUserRoleRequest request) {
        return userService.modifyRoleForUser(request.getUsername(), request.getRoleName());
    }
}
