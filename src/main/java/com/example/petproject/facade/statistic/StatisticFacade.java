package com.example.petproject.facade.statistic;

import com.example.petproject.dto.response.StatisticResponse;

import java.util.List;

public interface StatisticFacade {
    List<StatisticResponse> getStatistic();
}
