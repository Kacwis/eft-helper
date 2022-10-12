package com.example.efthelper.model.repository;

import com.example.efthelper.model.Ammunition;
import com.example.efthelper.model.HideoutStation;

import java.util.List;
import java.util.Optional;

public interface HideoutStationRepository {

    List<HideoutStation> findAll();

    Optional<HideoutStation> findById(Integer id);

    HideoutStation save(HideoutStation station);

    boolean existsById(Integer id);
}
