package com.example.petproject.dto.response;

public class UniversityResponse {

    private long id;
    private String name;
    private int studyDuration;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudyDuration(int studyDuration) {
        this.studyDuration = studyDuration;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudyDuration() {
        return studyDuration;
    }
}
