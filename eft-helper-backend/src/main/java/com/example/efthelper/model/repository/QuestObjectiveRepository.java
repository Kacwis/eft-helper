package com.example.efthelper.model.repository;

import com.example.efthelper.model.Item;
import com.example.efthelper.model.QuestObjective;

import java.util.List;
import java.util.Optional;

public interface QuestObjectiveRepository {

    List<QuestObjective> findAll();

    Optional<QuestObjective> findById(Integer id);

    QuestObjective save(QuestObjective objective);

    boolean existsById(Integer id);

}
