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
    // TODO: 23.06.2023 имеет право на жизнь, но тоже есть нюансы.
    //  Как с конвертером общаг пофиксишь - пни, нырнем тут глубже
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

        Map<Long, University> universities = source.stream()
                .map(StudentRequest::getUniversity)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), universityService::getUniversity));

        Map<Long, Dormitory> dormitories = source.stream()
                .map(StudentRequest::getDormitory)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), dormitoryService::getDormitory));

        Map<Long, Room> rooms = source.stream()
                .map(StudentRequest::getRoom)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), roomService::getRoom));

        return source.stream()
                .map(request -> mapToStudent(
                        request,
                        universities.get(request.getUniversity()),
                        dormitories.get(request.getDormitory()),
                        rooms.get(request.getRoom())
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
