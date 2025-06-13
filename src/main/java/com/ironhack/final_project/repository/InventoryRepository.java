package com.ironhack.final_project.repository;

import com.ironhack.final_project.model.Inventory;
import com.ironhack.final_project.model.Item;
import com.ironhack.final_project.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByPlayer(Player player);
    List<Inventory> findByItems(List<Item> items);
}
