package com.example.efthelper.model.repository;

import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.ModuleRequirement;

import java.util.List;
import java.util.Optional;

public interface ModuleRequirementRepository {

    List<ModuleRequirement> findAll();

    Optional<ModuleRequirement> findById(Integer id);

    ModuleRequirement save(ModuleRequirement requirement);

    boolean existsById(Integer id);

}
