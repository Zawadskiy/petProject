package com.example.petproject.facade.university;

import com.example.petproject.dto.request.modify.UniversityRequest;
import com.example.petproject.dto.response.UniversityResponse;

import java.util.List;

public interface UniversityFacade {
    UniversityResponse updateUniversity(UniversityRequest request, long id);
    UniversityResponse getUniversity(long id);
    UniversityResponse createUniversity(UniversityRequest request);
    UniversityResponse deleteUniversity(long id);
    List<UniversityResponse> getUniversities(int page, int size);
}
