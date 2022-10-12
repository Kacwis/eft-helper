package com.example.efthelper.model.repository;

import com.example.efthelper.model.Item;
import com.example.efthelper.model.Trader;

import java.util.List;
import java.util.Optional;

public interface TraderRepository {

    List<Trader> findAll();

    Optional<Trader> findById(Integer id);

    Trader save(Trader trader);

    boolean existsById(Integer id);
}
