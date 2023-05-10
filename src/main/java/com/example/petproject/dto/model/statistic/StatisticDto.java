package com.example.petproject.dto.model.statistic;

import com.example.petproject.dto.model.dormitory.DormitoryDto;

import java.util.List;

public class StatisticDto {

    private String universityName;
    private List<DormitoryStatisticDto> dormitoryStatistic;
    private int studentsCount;
    private long liveInDormitory;

    public int getStudentsCount() {
        return studentsCount;
    }

    public long getLiveInDormitory() {
        return liveInDormitory;
    }


    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setLiveInDormitory(long liveInDormitory) {
        this.liveInDormitory = liveInDormitory;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<DormitoryStatisticDto> getDormitoryStatistic() {
        return dormitoryStatistic;
    }

    public void setDormitoryStatistic(List<DormitoryStatisticDto> dormitoryStatistic) {
        this.dormitoryStatistic = dormitoryStatistic;
    }
}
