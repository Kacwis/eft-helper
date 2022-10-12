package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.ModuleRequirement;
import com.example.efthelper.model.repository.ModuleRequirementRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaModuleRequirementRepository extends JpaRepository<ModuleRequirement, Integer>, ModuleRequirementRepository {
}
