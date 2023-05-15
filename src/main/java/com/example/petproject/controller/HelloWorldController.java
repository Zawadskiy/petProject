package com.example.petproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// TODO: 16.05.2023 Актуатор или явный хелз чек? Все лучше, чем это чудо)
public class HelloWorldController {

    @PostMapping(value = "/")
    public String helloWorld() {
        return "Hello World";
    }
}
