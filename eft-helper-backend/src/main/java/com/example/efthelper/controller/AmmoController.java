package com.example.efthelper.controller;


import com.example.efthelper.model.Ammunition;
import com.example.efthelper.model.Item;
import com.example.efthelper.model.repository.AmmoRepository;
import com.example.efthelper.model.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ammo")
public class AmmoController {

    final private AmmoRepository repository;

    public AmmoController(final AmmoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Ammunition>> readAllAmmo(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Ammunition> saveAmmo(Ammunition ammo){
        var result = repository.save(ammo);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
