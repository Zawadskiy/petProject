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
    public List<Student> findStudentsByUniversity(String name) {
        return studentRepository.findByUniversityName(name);
    }

    @Override
    public List<Student> findStudentsByDormitory(String number) {
        return studentRepository.findByDormitoryNumber(number);
    }

    @Override
    @Transactional
    public Student update(Student update) {
        Student student = findById(update.getId());

        student.setRoom(update.getRoom());
        student.setDormitory(update.getDormitory());
//        student.setGender(update.getGender()); // ну а вдруг?) Орнул
        student.setUniversity(update.getUniversity());
        student.setName(update.getName());
        //TODO При установке университета нужно устанавливать дату поступления и окончания в сеттерах, убрать явную возможность изменения через сеттеріі.
        // При установке student.setDormitory устанавливать setLiveInDormitory, также переработать сеттеріі.

        return studentRepository.save(student);
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }
}
