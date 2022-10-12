package com.example.efthelper.model.repository;

import com.example.efthelper.model.QuestObjective;
import com.example.efthelper.model.QuestReputation;

import java.util.List;
import java.util.Optional;

public interface QuestReputationRepository {

    List<QuestReputation> findAll();

    Optional<QuestReputation> findById(Integer id);

    QuestReputation save(QuestReputation reputation);

    boolean existsById(Integer id);
}
