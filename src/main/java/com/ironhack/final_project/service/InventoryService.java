package com.ironhack.final_project.service;

import com.ironhack.final_project.dto.InventoryRequestDTO;
import com.ironhack.final_project.exception.InventoryNotFoundException;
import com.ironhack.final_project.model.Inventory;
import com.ironhack.final_project.model.Item;
import com.ironhack.final_project.model.Player;
import com.ironhack.final_project.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory create (InventoryRequestDTO inventoryRequestDTO) {
        Inventory newInventory = new Inventory();
        if (inventoryRequestDTO.getSpace() != 0) {
            newInventory.setSpace(inventoryRequestDTO.getSpace());
        }
        if (inventoryRequestDTO.getPlayer() != null) {
            newInventory.setPlayer(inventoryRequestDTO.getPlayer());
        }
        if (inventoryRequestDTO.getItems() != null) {
            newInventory.setItems(inventoryRequestDTO.getItems());
        }
        return inventoryRepository.save(newInventory);
    }

    public Inventory update(Long id, InventoryRequestDTO inventoryRequestDTO) {
        Optional<Inventory> optionalInventory = getInventory(id);
        Inventory inventoryFromDb = optionalInventory.get();
        inventoryFromDb.setSpace(inventoryRequestDTO.getSpace());
        inventoryFromDb.setPlayer(inventoryRequestDTO.getPlayer());
        inventoryFromDb.setItems(inventoryRequestDTO.getItems());
        return inventoryRepository.save(inventoryFromDb);
    }

    public Inventory updateItems(Long id, InventoryRequestDTO inventoryRequestDTO) {
        Optional<Inventory> optionalInventory = getInventory(id);
        Inventory inventoryFromDb = optionalInventory.get();
        inventoryFromDb.setItems(inventoryRequestDTO.getItems());
        return inventoryRepository.save(inventoryFromDb);
    }

    public void delete(Long id) {
        boolean existsById = inventoryRepository.existsById(id);
        if (!existsById) { throw new InventoryNotFoundException(id.toString()); }
        else { inventoryRepository.deleteById(id); }
    }

    public List<Inventory> getAll() { return inventoryRepository.findAll(); }

    public Inventory getById(Long id) {
        Optional<Inventory> optionalInventory = getInventory(id);
        return optionalInventory.get();
    }

    public Inventory getByPlayer(Player player) {
        return inventoryRepository.findByPlayer(player);
    }

    public List<Inventory> getByItems(List<Item> items) {
        return inventoryRepository.findByItems(items);
    }

    private Optional<Inventory> getInventory(Long id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isEmpty()) {
            throw new InventoryNotFoundException(id.toString());
        }
        return optionalInventory;
    }
}
