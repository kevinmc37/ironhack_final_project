package com.ironhack.final_project.controller;

import com.ironhack.final_project.dto.ItemRequestDTO;
import com.ironhack.final_project.model.Item;
import com.ironhack.final_project.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getById(id));
    }

    // GET /coffees/by-name?name=Arabica
    @GetMapping("/name")
    public ResponseEntity<List<Item>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(itemService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<Item> create(@Valid @RequestBody ItemRequestDTO dto) {
        Item item = itemService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @Valid @RequestBody ItemRequestDTO dto) {
        Item item = itemService.update(id, dto);
        return ResponseEntity.ok(item);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> updateName(@PathVariable Long id, @Valid @RequestBody ItemRequestDTO dto) {
        return ResponseEntity.ok(itemService.updateName(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
