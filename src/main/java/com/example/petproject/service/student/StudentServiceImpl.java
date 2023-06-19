package com.example.petproject.service.student;

import com.example.petproject.domain.Student;
import com.example.petproject.exception.StudentNotFoundException;
import com.example.petproject.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> getStudents(Pageable pageRequest) {
        return studentRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student update(Student update) {
        return studentRepository.save(update);
    }

    @Override
    public Student getStudent(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    @Transactional
    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
