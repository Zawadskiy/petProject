package com.example.petproject.repository;

import com.example.petproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //насколько то хреновіій вариант?)
    @Query("select s from Student s where s.university.name in (:authorities) or s.dormitory.number in (:authorities) or 'ROLE_ADMIN' in :authorities")
    List<Student> findAll(List<String> authorities);
}
