package com.example.petproject.validator;

import com.example.petproject.domain.Room;
import com.example.petproject.domain.Student;
import com.example.petproject.domain.University;
import com.example.petproject.dto.request.modify.StudentRequest;
import com.example.petproject.service.room.RoomService;
import com.example.petproject.service.student.StudentService;
import com.example.petproject.service.university.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class StudentRequestValidator implements Validator {

    private final RoomService roomService;
    private final UniversityService universityService;
    private final StudentService studentService;

    @Autowired
    public StudentRequestValidator(RoomService roomService, UniversityService universityService,
                                   StudentService studentService) {
        this.roomService = roomService;
        this.universityService = universityService;
        this.studentService = studentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        if (target instanceof StudentRequest request) {

            Room room = roomService.getRoom(request.getRoom());
            University university = universityService.getUniversity(request.getUniversity());

            if(request.getAdmissionYear().plusMonths(university.getStudyDuration()).isAfter(request.getDeductionDate())) {
                errors.rejectValue("admissionYear/deductionDate", "бла бла бла");
            }

            if(!room.getResidentsGender().name().equalsIgnoreCase(request.getGender())) {
                errors.rejectValue("gender", "бла бла бла");
            }

            List<Student> students = studentService.getAllByRoom(request.getRoom());

            if(students.size() >= room.getCapacity()) {
                errors.rejectValue("room", "бла бла бла нет места");
            }
        }
    }
}
