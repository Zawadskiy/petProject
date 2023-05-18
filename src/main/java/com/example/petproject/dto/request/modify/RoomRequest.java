package com.example.petproject.dto.request.modify;

import com.example.petproject.model.Gender;

public class RoomRequest {

    private String number;
    private long dormitory;
    private int capacity;
    private boolean availabilityForAccommodation;
    private Gender residentsGender;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }

    public long getDormitory() {
        return dormitory;
    }

    public void setDormitory(long dormitory) {
        this.dormitory = dormitory;
    }

    public Gender getResidentsGender() {
        return residentsGender;
    }

    public void setResidentsGender(Gender residentsGender) {
        this.residentsGender = residentsGender;
    }
}
