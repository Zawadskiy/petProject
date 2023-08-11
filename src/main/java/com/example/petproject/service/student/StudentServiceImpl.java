package com.example.petproject.service.student;

import com.example.petproject.domain.Room;
import com.example.petproject.domain.Student;
import com.example.petproject.repository.DormitoryRepository;
import com.example.petproject.repository.RoomRepository;
import com.example.petproject.repository.StudentRepository;
import com.example.petproject.repository.UniversityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UniversityRepository universityRepository;
    private final DormitoryRepository dormitoryRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              UniversityRepository universityRepository,
                              DormitoryRepository dormitoryRepository,
                              RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<Student> getAll(Pageable pageRequest) {
        return studentRepository.findAll(pageRequest);
    }

    @Override
    public Student getStudent(long id) {
        return studentRepository.findByIdEx(id);
    }

    @Override
    @Transactional
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student update(Student update) {

        Student student = studentRepository.findByIdEx(update.getId());

        student.setName(update.getName());
        student.setLiveInDormitory(update.isLiveInDormitory());
        student.setDeductionDate(update.getDeductionDate());
        student.setAdmissionYear(update.getAdmissionYear());
        student.setRoom(roomRepository.findByIdEx(update.getRoom().getId()));
        student.setUniversity(universityRepository.findByIdEx(update.getUniversity().getId()));
        student.setDormitory(dormitoryRepository.findByIdEx(update.getDormitory().getId()));
        return studentRepository.save(update);
    }

    @Override
    @Transactional
    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllIn(List<Long> id) {
        return studentRepository.findAllById(id);
    }

    @Override
    public List<Student> getAllByRoom(long id) {
        return studentRepository.findAllByRoomId(id);
    }
}
