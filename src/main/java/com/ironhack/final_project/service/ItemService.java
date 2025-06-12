package com.ironhack.final_project.service;

import com.ironhack.final_project.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Coffee create (CoffeeRequestDTO coffeeRequestDTO) {
        validateName(coffeeRequestDTO.getName());
        Coffee newCoffee = new Coffee();
        newCoffee.setName(coffeeRequestDTO.getName());
        newCoffee.setOrigin(coffeeRequestDTO.getOrigin());
        if (coffeeRequestDTO.getStrength() != null) {
            newCoffee.setStrength(coffeeRequestDTO.getStrength());
        }
        return coffeeRepository.save(newCoffee);
    }

    public Coffee update(Long id, CoffeeRequestDTO coffeeRequestDTO) {
        Optional<Coffee> optionalCoffee = getCoffee(id);
        Coffee coffeeFromDb = optionalCoffee.get();
        validateUpdatedName(coffeeFromDb, coffeeRequestDTO.getName());
        coffeeFromDb.setName(coffeeRequestDTO.getName());
        coffeeFromDb.setOrigin(coffeeRequestDTO.getOrigin());
        coffeeFromDb.setStrength(coffeeRequestDTO.getStrength());
        return coffeeRepository.save(coffeeFromDb);
    }

    public Coffee updateName(Long id, CoffeeNameRequest dto) {
        Optional<Coffee> optionalCoffee = getCoffee(id);
        Coffee coffeeFromDb = optionalCoffee.get();
        validateUpdatedName(coffeeFromDb, dto.getName());
        coffeeFromDb.setName(dto.getName());
        return coffeeRepository.save(coffeeFromDb);
    }

    public void delete(Long id) {
        boolean existsById = coffeeRepository.existsById(id);
        if (!existsById) { throw new CoffeeNotFoundException(id.toString()); }
        else { coffeeRepository.deleteById(id); }
    }

    public List<Coffee> getAll() { return coffeeRepository.findAll(); }

    public Coffee getById(Long id) {
        Optional<Coffee> optionalCoffee = getCoffee(id);
        return optionalCoffee.get();
    }

    public List<Coffee> getByStrength(int strength) {
        return coffeeRepository.findByStrength(strength);
    }

    public List<Coffee> getByStrengthGreaterThan(int minStrength) {
        return coffeeRepository.findByStrengthGreaterThan(minStrength);
    }

    public List<Coffee> getByOriginAndName(String origin, String name) {
        return coffeeRepository.findByOriginAndName(origin, name);
    }

    public List<Coffee> getByName(String name) {
        return coffeeRepository.findByName(name);
    }

    public List<Coffee> getStrongestByOrigin(String origin) {
        return coffeeRepository.findStrongestByOrigin(origin);
    }

    public List<Coffee> getByNameLengthGreaterThan(int minLength) {
        return coffeeRepository.findByNameLengthGreaterThan(minLength);
    }

    private Optional<Coffee> getCoffee(Long id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isEmpty()) {
            throw new CoffeeNotFoundException(id.toString());
        }
        return optionalCoffee;
    }

    private void validateName(String name) {
        boolean exists = coffeeRepository.existsByName(name);
        if (exists) {
            throw new BadRequestException("Coffee with name " + name + " already exists.");
        }
    }

    private void validateUpdatedName(Coffee coffeeFromDb, String dto) {
        if (!coffeeFromDb.getName().equalsIgnoreCase(dto)) { validateName(dto); }
    }
}
