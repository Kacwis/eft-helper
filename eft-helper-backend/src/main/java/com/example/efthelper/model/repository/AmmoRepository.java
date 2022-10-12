package com.example.efthelper.model.repository;

import com.example.efthelper.model.Ammunition;
import com.example.efthelper.model.Item;

import java.util.List;
import java.util.Optional;

public interface AmmoRepository {

    List<Ammunition> findAll();

    Optional<Ammunition> findById(String id);

    Ammunition save(Ammunition item);

    boolean existsById(String id);
}
