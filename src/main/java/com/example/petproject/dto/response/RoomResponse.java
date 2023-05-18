package com.example.petproject.dto.response;

import com.example.petproject.model.Gender;

public class RoomResponse {

    private long id;
    private String number;
    private long dormitory;
    private int capacity;
    private boolean availabilityForAccommodation;
    private Gender residentsGender;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDormitory(long dormitory) {
        this.dormitory = dormitory;
    }

    public void setResidentsGender(Gender residentsGender) {
        this.residentsGender = residentsGender;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public long getDormitory() {
        return dormitory;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }

    public Gender getResidentsGender() {
        return residentsGender;
    }
}
