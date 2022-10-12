package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.QuestObjective;
import com.example.efthelper.model.repository.QuestObjectiveRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuestObjectiveRepository extends JpaRepository<QuestObjective, Integer>, QuestObjectiveRepository {
}
