package com.example.efthelper.controller;

import com.example.efthelper.model.QuestReputation;
import com.example.efthelper.model.Trader;
import com.example.efthelper.model.projection.NewQuestReputationDTO;
import com.example.efthelper.model.repository.QuestReputationRepository;
import com.example.efthelper.model.repository.TraderRepository;
import com.example.efthelper.service.QuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/quest_reputation")
public class QuestReputationController {

    private final QuestService service;

    public QuestReputationController(final QuestService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<QuestReputation>> readAllQuestReputations(){
        return ResponseEntity.ok(service.findAllQuestReputations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestReputation> readQuestReputationById(@PathVariable Integer id){
        var result = service.findByIdQuestReputation(id);
        if(result == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<QuestReputation> saveQuestReputation(NewQuestReputationDTO questReputation){
        var result = service.saveQuestReputation(questReputation);
        return ResponseEntity.created(URI.create("/api/quest_reputation/" + result.getId())).body(result);
    }

}
