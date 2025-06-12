package com.ironhack.final_project.repository;

import com.ironhack.final_project.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {}
