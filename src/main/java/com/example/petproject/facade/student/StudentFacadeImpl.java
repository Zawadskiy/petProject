package com.example.petproject.facade.student;

import com.example.petproject.converter.StudentConverter;
import com.example.petproject.dto.model.statistic.StatisticDto;
import com.example.petproject.dto.model.student.StudentDto;
import com.example.petproject.model.Student;
import com.example.petproject.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final StudentConverter studentConverter;

    public StudentFacadeImpl(StudentService studentService, StudentConverter studentConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
    }

    @Override
    public List<StudentDto> getStudents(int page, int size) {
        List<Student> students = studentService.getStudents(page, size);

        return students.stream().map(studentConverter::toStudentDto).toList();
    }

    @Override
    public Student addStudent(StudentDto studentDto) {
        Student student = studentConverter.toStudent(studentDto);

        return studentService.addStudent(student);
    }
}
