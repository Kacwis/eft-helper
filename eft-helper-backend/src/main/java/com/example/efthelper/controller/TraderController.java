package com.example.efthelper.controller;

import com.example.efthelper.model.Ammunition;
import com.example.efthelper.model.Trader;
import com.example.efthelper.model.repository.AmmoRepository;
import com.example.efthelper.model.repository.TraderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/traders")
public class TraderController {

    final private TraderRepository repository;

    public TraderController(final TraderRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Trader>> readAllTraders(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Trader> saveTrader(Trader trader){
        var result = repository.save(trader);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
