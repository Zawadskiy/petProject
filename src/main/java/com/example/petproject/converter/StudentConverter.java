package com.example.petproject.converter;

import com.example.petproject.dto.response.StudentResponse;
import com.example.petproject.domain.Student;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StudentConverter implements Converter<Student, StudentResponse> {

    @Override
    public StudentResponse convert(Student source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<StudentResponse> convert(List<Student> source) {
        return source.stream()
                .map(this::mapToStudentResponse)
                .toList();
    }

    private StudentResponse mapToStudentResponse(Student student) {

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setLiveInDormitory(student.isLiveInDormitory());
        studentResponse.setGender(student.getGender().name());

        if (student.getDormitory() != null) {
            studentResponse.setDormitory(student.getDormitory().getId());
        }
        if (student.getRoom() != null) {
            studentResponse.setRoom(student.getRoom().getId());
        }
        if (student.getUniversity() != null) {
            studentResponse.setUniversity(student.getUniversity().getId());
        }

        studentResponse.setAdmissionYear(student.getAdmissionYear().toString());
        studentResponse.setDeductionDate(student.getDeductionDate().toString());

        return studentResponse;
    }
}
