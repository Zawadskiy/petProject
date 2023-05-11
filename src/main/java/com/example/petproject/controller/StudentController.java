package com.example.petproject.controller;

import com.example.petproject.dto.model.statistic.StatisticDto;
import com.example.petproject.dto.model.student.StudentDto;
import com.example.petproject.facade.statistic.StatisticFacade;
import com.example.petproject.facade.student.StudentFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
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

    @PutMapping("/update")
    public StudentDto updateStudent(@Valid @RequestBody StudentDto request) {
        return studentFacade.updateStudent(request);
    }

    @GetMapping("/get/{id}")
    public StudentDto getStudent(@PathVariable long id) {
        return studentFacade.getStudent(id);
    }

    @PostMapping ("/new")
    public StudentDto createStudent(@Valid @RequestBody StudentDto request) {
        return studentFacade.createStudent(request);
    }

    @DeleteMapping("/delete/{id}")
    public StudentDto deleteStudent(@PathVariable long id) {
        return studentFacade.deleteStudent(id);
    }
}
