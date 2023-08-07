package com.example.petproject.converter;

import com.example.petproject.domain.Dormitory;
import com.example.petproject.domain.Room;
import com.example.petproject.domain.Student;
import com.example.petproject.domain.University;
import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.model.Gender;
import com.example.petproject.service.dormitory.DormitoryService;
import com.example.petproject.service.room.RoomService;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StudentRequestConverter implements ConverterEx<StudentRequest, Student> {

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
    public Student convert(StudentRequest source) {
        return convert(Collections.singletonList(source)).get(0);
    }

    @Override
    public Student convert(StudentRequest source, long id) {

        Student student = convert(source);
        student.setId(id);

        return student;
    }

    @Override
    public List<Student> convert(List<StudentRequest> source) {

        List<Long> universities = source.stream()
                .map(StudentRequest::getUniversity)
                .distinct()
                .toList();
        Map<Long, University> universityMap = universityService.getAllIn(universities)
                .stream()
                .collect(Collectors.toMap(University::getId, Function.identity()));

        List<Long> dormitories = source.stream()
                .map(StudentRequest::getDormitory)
                .distinct()
                .toList();
        Map<Long, Dormitory> dormitoryMap = dormitoryService.getAllIn(dormitories)
                .stream()
                .collect(Collectors.toMap(Dormitory::getId, Function.identity()));

        List<Long> rooms = source.stream()
                .map(StudentRequest::getRoom)
                .distinct()
                .toList();
        Map<Long, Room> roomMap = roomService.getAllIn(rooms)
                .stream()
                .collect(Collectors.toMap(Room::getId, Function.identity()));


        return source.stream()
                .map(request -> mapToStudent(
                        request,
                        universityMap.get(request.getUniversity()),
                        dormitoryMap.get(request.getDormitory()),
                        roomMap.get(request.getRoom())
                )).toList();
    }

    private Student mapToStudent(StudentRequest studentRequest, University university, Dormitory dormitory, Room room) {

        Student student = new Student();

        student.setLiveInDormitory(studentRequest.isLiveInDormitory());
        student.setName(studentRequest.getName());
        student.setGender(Gender.valueOf(studentRequest.getGender()));
        student.setUniversity(university);
        student.setDormitory(dormitory);
        student.setRoom(room);

//        @TODO
//        student.setAdmissionYear();
//        student.setDeductionDate();

        return student;
    }
}
