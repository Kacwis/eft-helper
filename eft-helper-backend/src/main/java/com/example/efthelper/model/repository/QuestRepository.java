package com.example.efthelper.model.repository;

import com.example.efthelper.model.Quest;
import com.example.efthelper.model.QuestReputation;

import java.util.List;
import java.util.Optional;

public interface QuestRepository {

    List<Quest> findAll();

    Optional<Quest> findById(Integer id);

    Quest save(Quest quest);

    boolean existsById(Integer id);

}
