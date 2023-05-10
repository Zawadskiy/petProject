package com.example.petproject.controller;

import com.example.petproject.dto.response.statistic.StatisticResponse;
import com.example.petproject.model.Student;
import com.example.petproject.service.StatisticService;
import com.example.petproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final StatisticService statisticService;

    @Autowired
    public StudentController(StudentService studentService, StatisticService statisticService) {
        this.studentService = studentService;
        this.statisticService = statisticService;
    }

    @GetMapping(params = {"page", "size"})
    public List<Student> getStudents(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studentService.getStudents(page, size);
    }

    @GetMapping("/getstatistic")
    public List<StatisticResponse> getStatistic() {
        return statisticService.getStatistic();
    }
}
