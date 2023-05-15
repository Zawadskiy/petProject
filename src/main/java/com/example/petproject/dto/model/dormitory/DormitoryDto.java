package com.example.petproject.dto.model.dormitory;

// TODO: 16.05.2023 Переусложнил ты тут с пакетами.
//  К слову, запрос на создание сущности и
//  дто ответа объединять в один класс - прям такое себе.
//  Смысл при создании передавать id?
//  А что, если какие-то поля в ответе не фигурируют? А какие-то не обновляемы.
//  Уже три класса: респонс, реквест на создание и реквест на обнолвение
public class DormitoryDto {

    private long id;
    private String number;
    private int numberOfRooms;

    private String university;

    private boolean availabilityForAccommodation;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public boolean isAvailabilityForAccommodation() {
        return availabilityForAccommodation;
    }

    public void setAvailabilityForAccommodation(boolean availabilityForAccommodation) {
        this.availabilityForAccommodation = availabilityForAccommodation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
