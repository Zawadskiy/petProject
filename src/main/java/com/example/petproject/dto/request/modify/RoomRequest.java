package com.example.petproject.dto.request.modify;

import com.example.petproject.model.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RoomRequest {

    @NotEmpty
    private String number;

    @NotNull
    private long dormitory;

    @NotNull
    private int capacity;

    @NotNull
    private boolean availabilityForAccommodation;

    @NotEmpty
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
