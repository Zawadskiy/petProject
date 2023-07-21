package com.example.petproject.service.student;

import com.example.petproject.domain.Student;
import com.example.petproject.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        student.setDormitory(update.getDormitory());
        student.setName(update.getName());
        student.setRoom(update.getRoom());
        student.setUniversity(update.getUniversity());
        student.setLiveInDormitory(update.isLiveInDormitory());
//        student.setDeductionDate(update.getDeductionDate());
//        student.setAdmissionYear(update.getAdmissionYear());

        return studentRepository.save(update);
    }

    @Override
    @Transactional
    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
