package com.example.petproject.controllrs;

import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Gender;
import com.example.petproject.model.Room;
import com.example.petproject.model.University;
import com.example.petproject.repository.DormitoryRepository;
import com.example.petproject.repository.RoomRepository;
import com.example.petproject.repository.StudentRepository;
import com.example.petproject.repository.UniversityRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Resource
    UniversityRepository universityRepository;

    @Resource
    DormitoryRepository dormitoryRepository;

    @Resource
    RoomRepository roomRepository;

    @Resource
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String helloWorld() {
//        University university = new University();
//        university.setName("MyUniversity");
//        university.setStudyDuration(20);
//
//        Dormitory dormitory = new Dormitory();
//
//        dormitory.setNumber("1");
//        dormitory.setUniversity(university);
//        dormitory.setAvailabilityForAccommodation(true);
//        dormitory.setNumberOfRooms(3);
//
//        Room room1 = new Room();
//        room1.setCapacity(4);
//        room1.setDormitory(dormitory);
//        room1.setAvailabilityForAccommodation(true);
//        room1.setResidentsGender(Gender.Man);
//        room1.setNumber("1");
//
//        Room room2 = new Room();
//        room2.setCapacity(4);
//        room2.setDormitory(dormitory);
//        room2.setAvailabilityForAccommodation(true);
//        room2.setResidentsGender(Gender.Man);
//        room2.setNumber("2");
//
//        Room room3 = new Room();
//        room3.setCapacity(4);
//        room3.setDormitory(dormitory);
//        room3.setAvailabilityForAccommodation(true);
//        room3.setResidentsGender(Gender.Woman);
//        room3.setNumber("3");
//
//        universityRepository.save(university);
//        dormitoryRepository.save(dormitory);
//        roomRepository.save(room1);
//        roomRepository.save(room2);
//        roomRepository.save(room3);
        return "Hello World";
    }
}
