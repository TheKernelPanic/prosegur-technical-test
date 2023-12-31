package com.prosegur.technicaltest.technicaltest.controller;

import com.prosegur.technicaltest.technicaltest.dto.HighestScoreDto;
import com.prosegur.technicaltest.technicaltest.dto.ScoreDto;
import com.prosegur.technicaltest.technicaltest.exception.CustomerNotFound;
import com.prosegur.technicaltest.technicaltest.exception.OriEntityHasNoCustomers;
import com.prosegur.technicaltest.technicaltest.service.CustomerService;
import jakarta.validation.constraints.Pattern;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(@Lazy CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Servicio REST para la obtención de puntuación en base al DNI del cliente.
     */
    @GetMapping(
            path = "/get-score/{dni}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ScoreDto> getScore(
            @PathVariable @Pattern(regexp = "[a-zA-Z0-9]{8}[a-zA-Z]}") String dni
    ) {
        try {
            return ResponseEntity.ok(
                    this.customerService.getScore(dni)
            );
        } catch (CustomerNotFound $exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    $exception.getMessage()
            );
        }
    }

    /**
     * Servicio REST para la obtención del cliente con mayor puntuación en base a la entidad (ori entity).
     */
    @GetMapping(
            path = "/get-highest-score/{oriEntity}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HighestScoreDto> getHighestScore(
            @PathVariable String oriEntity
    ) {
        try {
            return ResponseEntity.ok(
                    this.customerService.getHighestScore(oriEntity)
            );
        } catch (OriEntityHasNoCustomers $exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    $exception.getMessage()
            );
        }
    }
}
