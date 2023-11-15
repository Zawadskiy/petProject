package com.example.petproject.domain;

import com.example.petproject.model.Gender;
import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;

    @ManyToOne
    private Dormitory dormitory;

    private int capacity;

    @Column(name = "accommodation_availability")
    private boolean accommodationAvailability;

    @Enumerated(EnumType.STRING)
    private Gender residentsGender;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAccommodationAvailability() {
        return accommodationAvailability;
    }

    public void setAccommodationAvailability(boolean accommodationAvailability) {
        this.accommodationAvailability = accommodationAvailability;
    }

    public Gender getResidentsGender() {
        return residentsGender;
    }

    public void setResidentsGender(Gender residentsGender) {
        this.residentsGender = residentsGender;
    }
}
