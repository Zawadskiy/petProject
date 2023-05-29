package com.example.petproject.service.student;

import com.example.petproject.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents(int page, int size);
    Student create(Student student);
    List<Student> getStudentsByUniversityId(long universityId);
    List<Student> getByDormitoryId(long dormitoryId);
    Student update(Student student);
    Student getStudent(long id);
    void delete(long id);
}
