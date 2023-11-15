package com.example.petproject.controller;

import com.example.petproject.converter.Converter;
import com.example.petproject.converter.StudentConverter;
import com.example.petproject.converter.StudentRequestConverter;
import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.dto.response.StudentResponse;
import com.example.petproject.domain.Student;
import com.example.petproject.service.student.StudentService;
import com.example.petproject.validator.StudentRequestValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    private final StudentRequestConverter studentRequestConverter;
    private final StudentConverter studentConverter;

    private final StudentRequestValidator studentRequestValidator;

    @InitBinder("studentRequest")
    void initStudentValidator(WebDataBinder binder) {
        binder.setValidator(studentRequestValidator);
    }

    @Autowired
    public StudentController(StudentService studentService,
                             StudentRequestConverter studentRequestConverter,
                             StudentConverter studentConverter,
                             StudentRequestValidator studentRequestValidator) {
        this.studentService = studentService;
        this.studentRequestConverter = studentRequestConverter;
        this.studentConverter = studentConverter;
        this.studentRequestValidator = studentRequestValidator;
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@Valid @RequestBody StudentRequest request, @PathVariable long id) {

        Student student = studentRequestConverter.convert(request, id);
        Student updatedStudent = studentService.update(student);

        return new ResponseEntity<>(studentConverter.convert(updatedStudent), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> get(@PathVariable long id) {

        Student student = studentService.getStudent(id);

        return new ResponseEntity<>(studentConverter.convert(student), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest request) {

        Student convert = studentRequestConverter.convert(request);

        Student student = studentService.create(convert);

        return new ResponseEntity<>(studentConverter.convert(student), HttpStatus.CREATED);
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
