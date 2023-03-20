package com.example.petproject.controller;

import com.example.petproject.dto.request.ModifyUserRoles;
import com.example.petproject.dto.response.UserInfoResponse;
import com.example.petproject.dto.response.statistic.Statistic;
import com.example.petproject.model.Student;
import com.example.petproject.service.StatisticService;
import com.example.petproject.service.StudentService;
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
    private final StudentService studentService;

    @Autowired
    public HelloWorldController(UserService userService,
                                StatisticService statisticService,
                                StudentService studentService) {
        this.userService = userService;
        this.statisticService = statisticService;
        this.studentService = studentService;
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

    @PostMapping("/admin/users/role/add")
    public UserInfoResponse addRoleForUser(@Valid @RequestBody ModifyUserRoles request) {
        return userService.addRoleForUser(request.getUsername(), request.getRoleName());
    }

    @PostMapping("/admin/users/role/delete")
    public UserInfoResponse deleteRoleForUser(@Valid @RequestBody ModifyUserRoles request) {
        return userService.deleteRoleForUser(request.getUsername(), request.getRoleName());
    }

    //TODO добавити в security контроллер
    @GetMapping("/getstatistic")
    public List<Statistic> getStatistic() {
        return statisticService.getStatistic();
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
}
