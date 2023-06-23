package com.example.petproject.dto.request.modify;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UniversityRequest {

    @NotEmpty
    private String name;

    @NotNull
    private int studyDuration;

    public String getName() {
        return name;
    }

    public int getStudyDuration() {
        return studyDuration;
    }

}
