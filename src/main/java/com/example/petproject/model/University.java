package com.example.petproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    //in months
    private int studyDuration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(int studyDuration) {
        this.studyDuration = studyDuration;
    }
}
