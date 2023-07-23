package com.prosegur.technicaltest.technicaltest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HighestScoreDto {

    @JsonProperty("customer_dni")
    public String customerDni;
}
