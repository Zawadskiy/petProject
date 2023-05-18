package com.example.petproject.facade.student;

import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.dto.response.StudentResponse;

import java.util.List;

public interface StudentFacade {
    List<StudentResponse> getStudents(int page, int size);

    StudentResponse updateStudent(StudentRequest request, long id);

    StudentResponse getStudent(long id);

    StudentResponse createStudent(StudentRequest request);

    StudentResponse deleteStudent(long id);
}
