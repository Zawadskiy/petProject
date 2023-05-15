package com.example.petproject.dto.model.statistic;

public class DormitoryStatisticDto {
    // TODO: 16.05.2023 модификаторы доступа?
    String name;
    int freePlaces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }
}
