package com.example.petproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @PostMapping(value = "/")
    public String helloWorld() {
        return "Hello World";
    }
}
