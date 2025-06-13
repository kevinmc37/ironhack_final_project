package com.ironhack.final_project.controller;

import com.ironhack.final_project.dto.InventoryRequestDTO;
import com.ironhack.final_project.model.Inventory;
import com.ironhack.final_project.model.Item;
import com.ironhack.final_project.model.Player;
import com.ironhack.final_project.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @GetMapping("/player")
    public ResponseEntity<Inventory> getByPlayer(@RequestParam Player player) {
        return ResponseEntity.ok(inventoryService.getByPlayer(player));
    }

    @GetMapping("/items")
    public ResponseEntity<List<Inventory>> getByItems(@RequestParam List<Item> items) {
        return ResponseEntity.ok(inventoryService.getByItems(items));
    }

    @PostMapping
    public ResponseEntity<Inventory> create(@Valid @RequestBody InventoryRequestDTO dto) {
        Inventory inventory = inventoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> update(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO dto) {
        Inventory inventory = inventoryService.update(id, dto);
        return ResponseEntity.ok(inventory);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Inventory> updateItems(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO dto) {
        return ResponseEntity.ok(inventoryService.updateItems(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
