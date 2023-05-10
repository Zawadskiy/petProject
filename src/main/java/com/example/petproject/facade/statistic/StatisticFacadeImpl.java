package com.example.petproject.facade.statistic;

import com.example.petproject.dto.model.statistic.DormitoryStatisticDto;
import com.example.petproject.dto.model.statistic.StatisticDto;
import com.example.petproject.model.Dormitory;
import com.example.petproject.model.Room;
import com.example.petproject.model.Student;
import com.example.petproject.model.University;
import com.example.petproject.service.DormitoryService;
import com.example.petproject.service.RoomService;
import com.example.petproject.service.StudentService;
import com.example.petproject.service.UniversityService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StatisticFacadeImpl implements StatisticFacade {

    private final UniversityService universityService;
    private final DormitoryService dormitoryService;
    private final RoomService roomService;
    private final StudentService studentService;


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
    public List<StatisticDto> getStatistic() {

        List<University> universities = universityService.findAll();

        return universities.stream().map(this::getStatisticByUniversity).toList();
    }

    private StatisticDto getStatisticByUniversity(University university) {
        StatisticDto statistic = new StatisticDto();
        List<Student> students = studentService.findStudentsByUniversity(university.getName());

        List<Dormitory> dormitories = dormitoryService.findAllAvailableForAccommodation();

        statistic.setDormitoryStatistic(dormitories.stream().map(this::getStatisticByDormitory).toList());


        statistic.setLiveInDormitory(students.stream().filter(student -> student.getDormitory() != null).count());
        statistic.setStudentsCount(students.size());
        statistic.setUniversityName(university.getName());

        return statistic;
    }

    private DormitoryStatisticDto getStatisticByDormitory(Dormitory dormitory) {
        DormitoryStatisticDto statistic = new DormitoryStatisticDto();
        List<Room> roomsInDormitory = roomService.findByDormitoryId(dormitory.getId());

        int capacity = roomsInDormitory.stream().mapToInt(Room::getCapacity).sum();
        int studentsInDormitory = studentService.findStudentsByDormitory(dormitory.getNumber()).size();

        statistic.setName(dormitory.getNumber());
        statistic.setFreePlaces(capacity-studentsInDormitory);

        return statistic;
    }
}
