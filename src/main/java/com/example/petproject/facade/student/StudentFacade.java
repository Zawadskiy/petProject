package com.example.petproject.facade.student;

import com.example.petproject.dto.model.student.StudentDto;

import java.util.List;

public interface StudentFacade {
    List<StudentDto> getStudents(int page, int size);

    StudentDto updateStudent(StudentDto request);

    StudentDto getStudent(long id);

    StudentDto createStudent(StudentDto request);

    StudentDto deleteStudent(long id);
}
