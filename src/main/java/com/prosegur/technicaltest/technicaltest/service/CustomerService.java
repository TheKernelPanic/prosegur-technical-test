package com.prosegur.technicaltest.technicaltest.service;

import com.prosegur.technicaltest.technicaltest.dto.HighestScoreDto;
import com.prosegur.technicaltest.technicaltest.dto.ScoreDto;
import com.prosegur.technicaltest.technicaltest.exception.CustomerNotFound;
import com.prosegur.technicaltest.technicaltest.exception.OriEntityHasNoCustomers;

public interface CustomerService {

    ScoreDto getScore(String dni) throws CustomerNotFound;
    HighestScoreDto getHighestScore(String oriEntity) throws OriEntityHasNoCustomers;
}
