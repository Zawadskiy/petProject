package com.example.petproject.converter;

import com.example.petproject.domain.Dormitory;
import com.example.petproject.domain.Room;
import com.example.petproject.domain.Student;
import com.example.petproject.domain.University;
import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.model.*;
import com.example.petproject.service.dormitory.DormitoryService;
import com.example.petproject.service.room.RoomService;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StudentRequestConverter implements Converter<StudentRequest, Student> {

    private final DormitoryService dormitoryService;
    private final UniversityService universityService;
    private final RoomService roomService;

    @Autowired
    public StudentRequestConverter(DormitoryService dormitoryService,
                                   UniversityService universityService,
                                   RoomService roomService) {
        this.dormitoryService = dormitoryService;
        this.universityService = universityService;
        this.roomService = roomService;
    }

    @Override
    // TODO: 23.06.2023 имеет право на жизнь, но тоже есть нюансы.
    //  Как с конвертером общаг пофиксишь - пни, нырнем тут глубже
    public Student convert(StudentRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public List<Student> convert(List<StudentRequest> source) {
        return source.stream()
                .map(this::mapToStudent)
                .toList();
    }

    private Student mapToStudent(StudentRequest studentRequest) {

        Student student = new Student();

        student.setLiveInDormitory(studentRequest.isLiveInDormitory());
        student.setName(studentRequest.getName());
        student.setGender(Gender.valueOf(studentRequest.getGender()));

        University university = universityService.getUniversity(studentRequest.getUniversity());
        student.setUniversity(university);

        Dormitory dormitory = dormitoryService.getDormitory(studentRequest.getDormitory());
        student.setDormitory(dormitory);

        Room room = roomService.getRoom(studentRequest.getRoom());
        student.setRoom(room);

//        @TODO
//        student.setAdmissionYear();
//        student.setDeductionDate();

        return student;
    }
}
