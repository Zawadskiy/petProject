package com.example.petproject.model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ERole name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
