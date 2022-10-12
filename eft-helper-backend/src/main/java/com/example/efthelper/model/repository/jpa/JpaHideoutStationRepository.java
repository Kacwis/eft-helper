package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.repository.HideoutStationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface JpaHideoutStationRepository extends JpaRepository<HideoutStation, Integer>, HideoutStationRepository {
}
