package com.prosegur.technicaltest.technicaltest.service;

import com.prosegur.technicaltest.technicaltest.dto.ScoreDto;
import com.prosegur.technicaltest.technicaltest.exception.CustomerNotFound;

public interface CustomerService {
    ScoreDto getScore(String dni) throws CustomerNotFound;
}
