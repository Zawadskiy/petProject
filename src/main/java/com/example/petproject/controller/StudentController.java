package com.example.petproject.controller;

import com.example.petproject.dto.model.statistic.StatisticDto;
import com.example.petproject.dto.model.student.StudentDto;
import com.example.petproject.facade.statistic.StatisticFacade;
import com.example.petproject.facade.student.StudentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentFacade studentFacade;

    private final StatisticFacade statisticFacade;

    @Autowired
    public StudentController(StudentFacade studentFacade, StatisticFacade statisticFacade) {
        this.studentFacade = studentFacade;
        this.statisticFacade = statisticFacade;
    }

    @GetMapping(params = {"page", "size"})
    public List<StudentDto> getStudents(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studentFacade.getStudents(page, size);
    }

    @GetMapping("/getstatistic")
    public List<StatisticDto> getStatistic() {
        return statisticFacade.getStatistic();
    }
}
