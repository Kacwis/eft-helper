package com.example.efthelper.controller;


import com.example.efthelper.model.Ammunition;
import com.example.efthelper.model.Map;
import com.example.efthelper.model.repository.AmmoRepository;
import com.example.efthelper.model.repository.MapRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    final private MapRepository repository;

    public MapController(final MapRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Map>> readAllMaps(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Map> saveMap
            (@RequestBody Map map){
        System.out.println(map.getId() + " " + map.getName());
        var result = repository.save(map);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

}
