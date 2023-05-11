package com.example.petproject.converter;

import com.example.petproject.dto.model.student.StudentDto;
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

    public Student toStudent(StudentDto studentDto) {
        Student student = new Student();

        student.setId(studentDto.getId());
        student.setLiveInDormitory(studentDto.isLiveInDormitory());
        student.setName(studentDto.getName());
        student.setGender(Gender.valueOf(studentDto.getGender()));

        University university = universityService.findByName(studentDto.getUniversityName());
        student.setUniversity(university);

        Dormitory dormitory = dormitoryService.findByNumberAndUniversityId(studentDto.getDormitoryNumber(), university.getId());
        student.setDormitory(dormitory);

        Room room = roomService.findByNumberAndDormitoryId(studentDto.getRoomNumber(), dormitory.getId());
        student.setRoom(room);

//        @TODO
//        student.setAdmissionYear();
//        student.setDeductionDate();

        return student;
    }

    public StudentDto toStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();

        studentDto.setId(student.getId());
//        studentDto.setDormitoryNumber(student.getDormitory().getNumber());
//        studentDto.setGender(student.getGender().name());
//        studentDto.setRoomNumber(student.getRoom().getNumber());
        studentDto.setUniversityName(student.getUniversity().getName());
        studentDto.setName(student.getName());
//        studentDto.setLiveInDormitory(student.isLiveInDormitory());

//        @TODO
//        studentDto.setAdmissionYear();
//        studentDto.setDeductionDate();

        return studentDto;
    }
}
