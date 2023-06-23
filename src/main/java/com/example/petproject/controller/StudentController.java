package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.dto.response.StudentResponse;
import com.example.petproject.domain.Student;
import com.example.petproject.service.student.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    private final Converter<StudentRequest, Student> studentConverter;
    private final Converter<Student, StudentResponse> responseConverter;

    @Autowired
    public StudentController(StudentService studentService,
                             Converter<StudentRequest, Student> studentConverter,
                             Converter<Student, StudentResponse> responseConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
        this.responseConverter = responseConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@Valid @RequestBody StudentRequest request, @PathVariable long id) {

        Student convert = studentConverter.convert(request);
        convert.setId(id);

        Student update = studentService.update(convert);

        return new ResponseEntity<>(responseConverter.convert(update), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> get(@PathVariable long id) {

        Student student = studentService.getStudent(id);

        return new ResponseEntity<>(responseConverter.convert(student), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest request) {

        Student convert = studentConverter.convert(request);

        Student student = studentService.create(convert);

        return new ResponseEntity<>(responseConverter.convert(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        studentService.delete(id);

        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAll(@PageableDefault Pageable pageRequest) {

        Page<Student> students = studentService.getAll(pageRequest);

        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
