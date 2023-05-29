package com.example.petproject.converter;

import com.example.petproject.dto.response.StudentResponse;
import com.example.petproject.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentToStudentResponse implements Converter<Student, StudentResponse> {
    @Override
    public StudentResponse convert(Student source) {

        StudentResponse studentDto = new StudentResponse();

        studentDto.setId(source.getId());
        studentDto.setName(source.getName());
        studentDto.setLiveInDormitory(source.isLiveInDormitory());
        studentDto.setGender(source.getGender().name());

        if (source.getDormitory() != null) {
            studentDto.setDormitory(source.getDormitory().getId());
        }
        if (source.getRoom() != null) {
            studentDto.setRoom(source.getRoom().getId());
        }
        if (source.getUniversity() != null) {
            studentDto.setUniversity(source.getUniversity().getId());
        }

//        @TODO
//        studentDto.setAdmissionYear();
//        studentDto.setDeductionDate();

        return studentDto;
    }

    @Override
    public List<StudentResponse> convert(List<Student> source) {
        return null;
    }
}
