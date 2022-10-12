package com.example.efthelper.model.repository.jpa;


import com.example.efthelper.model.Ammunition;
import com.example.efthelper.model.repository.AmmoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAmmoRepository extends JpaRepository<Ammunition, String>, AmmoRepository {
}
