package com.example.petproject.service.student;

import com.example.petproject.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents(int page, int size);
    Student create(Student student);
    List<Student> findStudentsByUniversity(String name);
    List<Student> findStudentsByDormitory(String number);
    Student update(Student student);
    Student findById(long id);
    void deleteById(long id);
}
