package com.ironhack.final_project.repository;

import com.ironhack.final_project.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);
    boolean existsByName(String name);
}
