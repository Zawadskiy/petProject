package com.example.petproject.dto.response;

public class DormitoryStatisticResponse {

    private String name;
    private int freePlaces;

    public void setName(String name) {
        this.name = name;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public String getName() {
        return name;
    }

    public int getFreePlaces() {
        return freePlaces;
    }
}
