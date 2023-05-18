package com.example.petproject.facade.statistic;

import com.example.petproject.dto.response.DormitoryStatisticResponse;
import com.example.petproject.dto.response.StatisticResponse;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Room;
import com.example.petproject.model.Student;
import com.example.petproject.model.University;
import com.example.petproject.service.dormitory.DormitoryService;
import com.example.petproject.service.room.RoomService;
import com.example.petproject.service.student.StudentService;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StatisticFacadeImpl implements StatisticFacade {
    // TODO: 16.05.2023 Тут сервисная логика
    private final UniversityService universityService;
    private final DormitoryService dormitoryService;
    private final RoomService roomService;
    private final StudentService studentService;

    @Autowired
    public StatisticFacadeImpl(UniversityService universityService,
                               DormitoryService dormitoryService,
                               RoomService roomService,
                               StudentService studentService) {
        this.universityService = universityService;
        this.dormitoryService = dormitoryService;
        this.roomService = roomService;
        this.studentService = studentService;
    }

    @Override
    public List<StatisticResponse> getStatistic() {
        return universityService.getAll().stream().map(this::getStatisticByUniversity).toList();
    }

    private StatisticResponse getStatisticByUniversity(University university) {

        StatisticResponse statistic = new StatisticResponse();
        List<Student> students = studentService.getStudentsByUniversityId(university.getId());
        List<Dormitory> dormitories = dormitoryService.getAllAvailableForAccommodation();

        statistic.setDormitoryStatistic(dormitories.stream().map(this::getStatisticByDormitory).toList());

        long liveInDormitory = students.stream().filter(student -> student.getDormitory() != null).count();

        statistic.setLiveInDormitory(liveInDormitory);
        statistic.setStudentsCount(students.size());
        statistic.setUniversityName(university.getName());

        return statistic;
    }

    // TODO: 16.05.2023 почему это все в одном классе?
    private DormitoryStatisticResponse getStatisticByDormitory(Dormitory dormitory) {

        DormitoryStatisticResponse statistic = new DormitoryStatisticResponse();
        List<Room> roomsInDormitory = roomService.getByDormitoryId(dormitory.getId());

        // TODO: 16.05.2023 Статистику логично делать гибкой. За период, еще какие-то параметры
        int capacity = roomsInDormitory.stream()
                .mapToInt(Room::getCapacity)
                .sum();

        int studentsInDormitory = studentService.getStudentsByDormitoryId(dormitory.getId())
                .size();

        statistic.setName(dormitory.getNumber());
        statistic.setFreePlaces(capacity-studentsInDormitory);

        return statistic;
    }
}
