package com.example.efthelper.controller;


import com.example.efthelper.model.Item;
import com.example.efthelper.model.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    final private ItemRepository repository;

    public ItemController(final ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Item>> readAllItems(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Item> saveItem(Item item){
        var result = repository.save(item);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
