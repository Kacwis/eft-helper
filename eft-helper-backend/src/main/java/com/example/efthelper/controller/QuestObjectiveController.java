package com.example.efthelper.controller;


import com.example.efthelper.model.QuestObjective;
import com.example.efthelper.model.QuestReputation;
import com.example.efthelper.model.projection.NewQuestReputationDTO;
import com.example.efthelper.service.QuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/quest_objectives")
public class QuestObjectiveController {

    private final QuestService service;

    public QuestObjectiveController(final QuestService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<QuestObjective>> readAllQuestObjectives(){
        return ResponseEntity.ok(service.findAllQuestObjectives());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestObjective> readQuestObjectiveById(@PathVariable Integer id){
        var result = service.findByIdQuestObjective(id);
        if(result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<QuestObjective> saveQuestReputation(QuestObjective questObjective){
        var result = service.saveQuestObjective(questObjective);
        return ResponseEntity.created(URI.create("/api/quest_objectives/" + result.getId())).body(result);
    }
}
