package com.example.petproject.service;

import com.example.petproject.exception.RoleNotFoundException;
import com.example.petproject.model.Student;
import com.example.petproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
        //TODO завязати запит на роль користувача
        String role = SecurityContextHolder
                .getContext()
                    .getAuthentication()
                        .getAuthorities()
                            .stream()
                                .map(GrantedAuthority::getAuthority)
                                    .findAny()
                                        .orElseThrow(() -> new RoleNotFoundException("User doesn't have any role"));

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> all = studentRepository.findAll(pageRequest);


        List<Student> allWhereUniversityNameIs = studentRepository
                .findByUniversityNameIgnoreCaseOrDormitoryNumber(role.substring(4).toLowerCase(), role.substring(4).toLowerCase(), pageRequest);
        List<Student> f = studentRepository
                .findByUniversityNameIgnoreCaseOrDormitoryNumber("test", "test", pageRequest);

        return all.stream().toList();
    }

    @Override
    public Student addStudent(Student student) {
        studentRepository.save(student);

        return student;
    }
}
