package com.example.petproject.service.student;

import com.example.petproject.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Page<Student> getAll(Pageable pageRequest);

    Student create(Student student);

    Student update(Student student);

    Student getStudent(long id);

    void delete(long id);
}
