package com.example.petproject.controllrs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String helloWorld() {
        return "Hello World";
    }
}
