package com.example.petproject.facade.student;

import com.example.petproject.dto.model.statistic.StatisticDto;
import com.example.petproject.dto.model.student.StudentDto;
import com.example.petproject.model.Student;

import java.util.List;

public interface StudentFacade {

    List<StudentDto> getStudents(int page, int size);

    Student addStudent(StudentDto student);

}
