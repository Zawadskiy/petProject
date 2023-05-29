package com.example.petproject.model;

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

    @Column(name = "available_for_accommodation")
    private boolean availabilityForAccommodation;

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

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }

    public Gender getResidentsGender() {
        return residentsGender;
    }

    public void setResidentsGender(Gender residentsGender) {
        this.residentsGender = residentsGender;
    }
}
