package com.example.petproject.controller;

import com.example.petproject.dto.request.AddRoleRequest;
import com.example.petproject.dto.response.UserInfoResponse;
import com.example.petproject.dto.response.statistic.Statistic;
import com.example.petproject.service.StatisticService;
import com.example.petproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    private final UserService userService;

    private final StatisticService statisticService;

    @Autowired
    public HelloWorldController(UserService userService,
                                StatisticService statisticService) {
        this.userService = userService;
        this.statisticService = statisticService;
    }

    @PostMapping(value = "/")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "user";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin/add/role")
    public UserInfoResponse addRoleForUser(@Valid @RequestBody AddRoleRequest addRoleRequest) {
        return userService.addRoleForUser(addRoleRequest.getUsername(), addRoleRequest.getRoleName());
    }

    //TODO добавити в security контроллер
    @GetMapping("/getstatistic")
    public List<Statistic> getStatistic() {
        return statisticService.getStatistic();
    }
}
