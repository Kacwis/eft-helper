package com.example.efthelper.model.repository;


import com.example.efthelper.model.HideoutModule;
import com.example.efthelper.model.Map;

import java.util.List;
import java.util.Optional;

public interface HideoutModuleRepository {

    List<HideoutModule> findAll();

    Optional<HideoutModule> findById(Integer id);

    HideoutModule save(HideoutModule module);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
