package com.example.petproject.facade.dormitory;

import com.example.petproject.dto.request.modify.DormitoryRequest;
import com.example.petproject.dto.response.DormitoryResponse;

import java.util.List;

public interface DormitoryFacade {
    DormitoryResponse updateDormitory(DormitoryRequest request, long id);
    DormitoryResponse getDormitory(long id);
    DormitoryResponse createDormitory(DormitoryRequest request);
    DormitoryResponse deleteDormitory(long id);
    List<DormitoryResponse> getDormitories(int page, int size);
}
