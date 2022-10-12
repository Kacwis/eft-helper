package com.example.efthelper.model.repository.jpa;


import com.example.efthelper.model.Trader;
import com.example.efthelper.model.repository.TraderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTraderRepository extends JpaRepository<Trader, Integer>, TraderRepository {
}
