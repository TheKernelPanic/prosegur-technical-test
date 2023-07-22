package com.prosegur.technicaltest.technicaltest.controller;

import com.prosegur.technicaltest.technicaltest.model.Client;
import com.prosegur.technicaltest.technicaltest.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private ScoreService scoreService;

    @Autowired
    public ClientController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping(path = "/get-score/{dni:[a-zA-Z0-9]{8}[a-zA-Z]}")
    public ResponseEntity<?> getScore(
            @PathVariable String dni
    ) {
        Client client = new Client();
        client.setDni("75451254v");
        client.setId(2L);
        client.setName("name");
        client.setFirstSurname("surname");

        return ResponseEntity.ok(client);
    }
}
