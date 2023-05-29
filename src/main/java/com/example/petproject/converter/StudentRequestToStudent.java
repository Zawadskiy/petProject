package com.example.petproject.converter;

import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.model.*;
import com.example.petproject.service.dormitory.DormitoryService;
import com.example.petproject.service.room.RoomService;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentRequestToStudent implements Converter<StudentRequest, Student> {

    private final DormitoryService dormitoryService;
    private final UniversityService universityService;
    private final RoomService roomService;

    @Autowired
    public StudentRequestToStudent(DormitoryService dormitoryService,
                                   UniversityService universityService,
                                   RoomService roomService) {
        this.dormitoryService = dormitoryService;
        this.universityService = universityService;
        this.roomService = roomService;
    }

    @Override
    public Student convert(StudentRequest source) {

        Student student = new Student();

        student.setLiveInDormitory(source.isLiveInDormitory());
        student.setName(source.getName());
        student.setGender(Gender.valueOf(source.getGender()));

        University university = universityService.getUniversity(source.getUniversity());
        student.setUniversity(university);

        Dormitory dormitory = dormitoryService.getDormitory(source.getDormitory());
        student.setDormitory(dormitory);

        Room room = roomService.getRoom(source.getRoom());
        student.setRoom(room);

//        @TODO
//        student.setAdmissionYear();
//        student.setDeductionDate();

        return student;
    }

    @Override
    public List<Student> convert(List<StudentRequest> source) {
        return null;
    }
}
