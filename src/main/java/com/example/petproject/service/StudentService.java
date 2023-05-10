package com.example.petproject.service;

import com.example.petproject.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents(int page, int size);

    Student addStudent(Student student);
}
