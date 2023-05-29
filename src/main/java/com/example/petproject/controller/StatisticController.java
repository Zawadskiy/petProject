package com.example.petproject.controller;

import com.example.petproject.dto.response.StatisticResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

//    private final StatisticService statisticService;

//    @GetMapping
//    public ResponseEntity<StatisticResponse> get() {
//        return new ResponseEntity<>(statisticService.get(), HttpStatus.OK);
//    }
}
