package com.example.efthelper.model.repository;

import com.example.efthelper.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    List<Item> findAll();

    Optional<Item> findById(String id);

    Item save(Item item);

    boolean existsById(String id);



}
