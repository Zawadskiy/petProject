package com.example.petproject.dto.response.statistic;

public class DormitoryDTO {

    private String number;

    private long freePlaces;

    public String getNumber() {
        return number;
    }

    public long getFreePlaces() {
        return freePlaces;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setFreePlaces(long freePlaces) {
        this.freePlaces = freePlaces;
    }
}
