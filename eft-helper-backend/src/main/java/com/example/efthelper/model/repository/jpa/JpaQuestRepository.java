package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.Quest;
import com.example.efthelper.model.repository.QuestRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuestRepository extends JpaRepository<Quest, Integer>, QuestRepository {


}
