package com.example.efthelper.model.repository.jpa;

import com.example.efthelper.model.Map;
import com.example.efthelper.model.repository.MapRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaMapRepository extends JpaRepository<Map, Integer>, MapRepository {
}
