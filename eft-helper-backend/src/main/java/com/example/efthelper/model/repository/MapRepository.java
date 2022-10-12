package com.example.efthelper.model.repository;


import com.example.efthelper.model.Item;
import com.example.efthelper.model.Map;

import java.util.List;
import java.util.Optional;

public interface MapRepository {

    List<Map> findAll();

    Optional<Map> findById(Integer id);

    Map save(Map map);

    boolean existsById(Integer id);

}
