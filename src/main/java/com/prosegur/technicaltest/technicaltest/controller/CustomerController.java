package com.prosegur.technicaltest.technicaltest.controller;

import com.prosegur.technicaltest.technicaltest.dto.ScoreDto;
import com.prosegur.technicaltest.technicaltest.exception.CustomerNotFound;
import com.prosegur.technicaltest.technicaltest.service.CustomerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(@Lazy CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(
            path = "/get-score/{dni:[a-zA-Z0-9]{8}[a-zA-Z]}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ScoreDto> getScore(
            @PathVariable String dni
    ) {
        try {
            return ResponseEntity.ok(
                    this.customerService.getScore(dni)
            );
        } catch (CustomerNotFound $e) {
            return ResponseEntity.notFound().build();
        }
    }



}
