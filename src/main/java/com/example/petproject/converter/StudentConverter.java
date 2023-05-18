package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.dto.response.StudentResponse;
import com.example.petproject.model.*;
import com.example.petproject.service.dormitory.DormitoryService;
import com.example.petproject.service.room.RoomService;
import com.example.petproject.service.university.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    private final DormitoryService dormitoryService;
    private final UniversityService universityService;
    private final RoomService roomService;

    public StudentConverter(DormitoryService dormitoryService, UniversityService universityService, RoomService roomService) {
        this.dormitoryService = dormitoryService;
        this.universityService = universityService;
        this.roomService = roomService;
    }

    public Student toStudent(StudentRequest studentDto) {

        Student student = new Student();

        student.setLiveInDormitory(studentDto.isLiveInDormitory());
        student.setName(studentDto.getName());
        student.setGender(Gender.valueOf(studentDto.getGender()));

        University university = universityService.getUniversity(studentDto.getUniversity());
        student.setUniversity(university);

        Dormitory dormitory = dormitoryService.getDormitory(studentDto.getDormitory());
        student.setDormitory(dormitory);

        Room room = roomService.getRoom(studentDto.getRoom());
        student.setRoom(room);

//        @TODO
//        student.setAdmissionYear();
//        student.setDeductionDate();

        return student;
    }

    public StudentResponse toStudentDto(Student student) {

        StudentResponse studentDto = new StudentResponse();

        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setLiveInDormitory(student.isLiveInDormitory());
        studentDto.setGender(student.getGender().name());

        if (student.getDormitory() != null) {
            studentDto.setDormitory(student.getDormitory().getId());
        }
        if (student.getRoom() != null) {
            studentDto.setRoom(student.getRoom().getId());
        }
        if (student.getUniversity() != null) {
            studentDto.setUniversity(student.getUniversity().getId());
        }

//        @TODO
//        studentDto.setAdmissionYear();
//        studentDto.setDeductionDate();

        return studentDto;
    }
}
