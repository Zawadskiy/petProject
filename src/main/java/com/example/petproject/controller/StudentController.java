package com.example.petproject.controller;

import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.dto.response.StatisticResponse;
import com.example.petproject.dto.response.StudentResponse;
import com.example.petproject.facade.statistic.StatisticFacade;
import com.example.petproject.facade.student.StudentFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<StudentResponse> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studentFacade.getStudents(page, size);
    }

    @GetMapping("/statistic")
    public List<StatisticResponse> get() {
        return statisticFacade.getStatistic();
    }

    @PutMapping("/{id}")
    public StudentResponse update(@Valid @RequestBody StudentRequest request, @PathVariable long id) {
        return studentFacade.updateStudent(request, id);
    }

    @GetMapping("/{id}")
    public StudentResponse get(@PathVariable long id) {
        return studentFacade.getStudent(id);
    }

    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentRequest request) {
        return studentFacade.createStudent(request);
    }

    @DeleteMapping("/{id}")
    public StudentResponse delete(@PathVariable long id) {
        return studentFacade.deleteStudent(id);
    }
}
