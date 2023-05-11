package com.example.petproject.facade.student;

import com.example.petproject.converter.StudentConverter;
import com.example.petproject.dto.model.student.StudentDto;
import com.example.petproject.model.Student;
import com.example.petproject.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final StudentConverter studentConverter;

    @Autowired
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
    public StudentDto updateStudent(StudentDto studentDto) {

        Student student = studentService.update(studentConverter.toStudent(studentDto));

        return studentConverter.toStudentDto(student);
    }

    @Override
    public StudentDto getStudent(long id) {

        Student student = studentService.findById(id);

        return studentConverter.toStudentDto(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        Student student = studentService.create(studentConverter.toStudent(studentDto));

        return studentConverter.toStudentDto(student);
    }

    @Override
    public StudentDto deleteStudent(long id) {

        studentService.deleteById(id);

        return new StudentDto();
    }
}
