package com.example.petproject.service;

import com.example.petproject.dto.response.statistic.DormitoryResponse;
import com.example.petproject.dto.response.statistic.StatisticResponse;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Room;
import com.example.petproject.model.Student;
import com.example.petproject.model.University;
import com.example.petproject.repository.RoomRepository;
import com.example.petproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StudentRepository studentRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public StatisticServiceImpl(StudentRepository studentRepository,
                                RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }

    //TODO занадто жирний) подумати над реалізацією пізнише
    @Override
    public List<StatisticResponse> getStatistic() {

        List<StatisticResponse> result = new ArrayList<>();

        //TODO модифікувати запит ан завежність від ролі користувача
        List<Student> allStudents = studentRepository.findAll();

        Map<University, List<Student>> universityStudents = allStudents.stream().collect(Collectors.groupingBy(Student::getUniversity));

        for (Map.Entry<University, List<Student>> universityListEntry : universityStudents.entrySet()) {

            StatisticResponse statistic = new StatisticResponse();
            List<Student> students = universityListEntry.getValue();

            Map<Dormitory, List<Student>> dormitoryStudents = students.stream().collect(Collectors.groupingBy(Student::getDormitory));

            List<DormitoryResponse> dormitoryDTOS = new ArrayList<>();
            for (Map.Entry<Dormitory, List<Student>> dormitoryListEntry : dormitoryStudents.entrySet()) {
                Dormitory dormitory = dormitoryListEntry.getKey();
                List<Student> studentsInDormitory = dormitoryListEntry.getValue();

                DormitoryResponse dormitoryResponse = new DormitoryResponse();

                dormitoryResponse.setNumber(dormitory.getNumber());

                dormitoryResponse.setFreePlaces(getAllPlacesInDormitory(dormitory) - studentsInDormitory.size());

                dormitoryDTOS.add(dormitoryResponse);
            }

            statistic.setDormitory(dormitoryDTOS);
            statistic.setStudentsCount(students.size());
            statistic.setLiveInDormitory(students.stream().filter(Student::isLiveInDormitory).count());
            statistic.setUniversityName(universityListEntry.getKey().getName());
            result.add(statistic);
        }

        return result;
    }

    private long getAllPlacesInDormitory(Dormitory dormitory) {
        List<Room> dormitoryRooms = roomRepository.findAllWhereDormitoryIs(dormitory.getId());

        return dormitoryRooms.stream()
                .flatMapToInt(r -> IntStream.of(r.getCapacity()))
                .sum();
    }
}
