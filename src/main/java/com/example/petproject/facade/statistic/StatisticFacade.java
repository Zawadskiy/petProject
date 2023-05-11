package com.example.petproject.facade.statistic;

import com.example.petproject.dto.model.statistic.StatisticDto;

import java.util.List;

public interface StatisticFacade {
    List<StatisticDto> getStatistic();
}
