package com.example.efthelper.model.repository.jpa;


import com.example.efthelper.model.Item;
import com.example.efthelper.model.repository.ItemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaItemRepository extends JpaRepository<Item, String> , ItemRepository {
}
