package com.ironhack.final_project.controller;

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
    public ResponseEntity<List<Coffee>> getAll() {
        return ResponseEntity.ok(coffeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(coffeeService.getById(id));
    }

    @GetMapping("/by-strength")
    public ResponseEntity<List<Coffee>> getByStrength(@RequestParam int strength) {
        return ResponseEntity.ok(coffeeService.getByStrength(strength));
    }

    // GET /coffees/stronger-than?minStrength=5
    @GetMapping("/stronger-than")
    public ResponseEntity<List<Coffee>> getByStrengthGreaterThan(@RequestParam int minStrength) {
        return ResponseEntity.ok(coffeeService.getByStrengthGreaterThan(minStrength));
    }

    // GET /coffees/search?origin=Brazil&name=Santos
    @GetMapping("/search")
    public ResponseEntity<List<Coffee>> getByOriginAndName(
            @RequestParam String origin,
            @RequestParam String name) {
        return ResponseEntity.ok(coffeeService.getByOriginAndName(origin, name));
    }

    // GET /coffees/by-name?name=Arabica
    @GetMapping("/by-name")
    public ResponseEntity<List<Coffee>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(coffeeService.getByName(name));
    }

    // GET /coffees/by-origin?origin=Ethiopia
    @GetMapping("/by-origin")
    public ResponseEntity<List<Coffee>> getStrongestByOrigin(@RequestParam String origin) {
        return ResponseEntity.ok(coffeeService.getStrongestByOrigin(origin));
    }

    // GET /coffees/long-names?minLength=10
    @GetMapping("/long-names")
    public ResponseEntity<List<Coffee>> getByNameLengthGreaterThan(@RequestParam int minLength) {
        return ResponseEntity.ok(coffeeService.getByNameLengthGreaterThan(minLength));
    }

    @PostMapping
    public ResponseEntity<Coffee> create(@Valid @RequestBody CoffeeRequestDTO dto) {
        Coffee coffee = coffeeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(coffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @Valid @RequestBody CoffeeRequestDTO dto) {
        Coffee coffee = coffeeService.update(id, dto);
        return ResponseEntity.ok(coffee);

        // return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Coffee> updateName(@PathVariable Long id, @Valid @RequestBody CoffeeNameRequest dto) {
        return ResponseEntity.ok(coffeeService.updateName(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coffeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
