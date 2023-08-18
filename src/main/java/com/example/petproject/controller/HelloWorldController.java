package com.example.petproject.controller;

// TODO: 18.08.2023 лишнее?)
import com.example.petproject.domain.University;
import com.example.petproject.service.student.StudentService;
import com.example.petproject.service.university.UniversityService;
import com.example.petproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// TODO: 16.05.2023 Актуатор или явный хелз чек? Все лучше, чем это чудо) єто напоминание прикруить актуатор
public class HelloWorldController {

    @PostMapping(value = "/")
    public String helloWorld() {
        return "Hello World";
    }
}
