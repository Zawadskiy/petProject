package com.example.petproject.service.student;

import com.example.petproject.model.Student;
import com.example.petproject.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<Student> getStudents(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> students = studentRepository.findAll(pageRequest);

        return students.getContent();
    }

    @Override
    @Transactional
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByUniversityId(long id) {
        return studentRepository.findByUniversityId(id);
    }

    @Override
    public List<Student> getStudentsByDormitoryId(long id) {
        return studentRepository.findAllByDormitoryId(id);
    }

    @Override
    @Transactional
    public Student update(Student update) {
        return studentRepository.save(update);
    }

    @Override
    public Student getStudent(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
