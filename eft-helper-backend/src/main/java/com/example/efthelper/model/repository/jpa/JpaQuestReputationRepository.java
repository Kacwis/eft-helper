package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.QuestReputation;
import com.example.efthelper.model.repository.QuestReputationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuestReputationRepository extends JpaRepository<QuestReputation, Integer>, QuestReputationRepository {
}
