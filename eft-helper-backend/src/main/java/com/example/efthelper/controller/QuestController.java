package com.example.efthelper.controller;


import com.example.efthelper.model.Quest;
import com.example.efthelper.model.QuestObjective;
import com.example.efthelper.model.projection.NewQuestDTO;
import com.example.efthelper.service.QuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/quests")
public class QuestController {

    private final QuestService service;

    public QuestController(final QuestService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Quest>> readAllQuests(){
        return ResponseEntity.ok(service.findAllQuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quest> readQuestById(@PathVariable Integer id){
        var result = service.findByIdQuest(id);
        if(result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Quest> saveQuestReputation(@RequestBody NewQuestDTO questDTO){
        var result = service.saveQuest(questDTO);
        return ResponseEntity.created(URI.create("/api/quests/" + result.getId())).body(result);
    }
}
