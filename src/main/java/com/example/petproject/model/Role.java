package com.example.petproject.model;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    // TODO: 16.05.2023 в чем ценность этой сущности, если роль одна у юзера и ролевая статическая?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
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
