package com.example.petproject.facade.student;

import com.example.petproject.converter.StudentConverter;
import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.dto.response.StudentResponse;
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
    public List<StudentResponse> getStudents(int page, int size) {

        List<Student> students = studentService.getStudents(page, size);

        return students.stream().map(studentConverter::toStudentDto).toList();
    }

    @Override
    public StudentResponse updateStudent(StudentRequest studentDto, long id) {

        Student student = studentConverter.toStudent(studentDto);
        student.setId(id);
        studentService.update(student);

        return studentConverter.toStudentDto(student);
    }

    @Override
    public StudentResponse getStudent(long id) {

        Student student = studentService.getStudent(id);

        return studentConverter.toStudentDto(student);
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentDto) {

        Student student = studentService.create(studentConverter.toStudent(studentDto));

        return studentConverter.toStudentDto(student);
    }

    @Override
    public StudentResponse deleteStudent(long id) {

        studentService.delete(id);

        return new StudentResponse();
    }
}
