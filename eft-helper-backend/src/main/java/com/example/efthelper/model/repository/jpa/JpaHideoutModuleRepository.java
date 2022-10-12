package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.HideoutModule;
import com.example.efthelper.model.repository.HideoutModuleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHideoutModuleRepository extends JpaRepository<HideoutModule, Integer>, HideoutModuleRepository {
}
