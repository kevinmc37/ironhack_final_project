package com.ironhack.final_project.service;

import com.ironhack.final_project.dto.ItemRequestDTO;
import com.ironhack.final_project.exception.BadRequestException;
import com.ironhack.final_project.exception.ItemNotFoundException;
import com.ironhack.final_project.model.Item;
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

    public Item create (ItemRequestDTO itemRequestDTO) {
        validateName(itemRequestDTO.getName());
        Item newItem = new Item();
        newItem.setName(itemRequestDTO.getName());
        if (itemRequestDTO.getQuantity() != 0) {
            newItem.setQuantity(itemRequestDTO.getQuantity());
        }
        if (itemRequestDTO.getDescription() != null) {
            newItem.setDescription(itemRequestDTO.getDescription());
        }
        if (itemRequestDTO.getWeight() != 0) {
            newItem.setWeight(itemRequestDTO.getWeight());
        }
        if (itemRequestDTO.getPrice() != 0) {
            newItem.setPrice(itemRequestDTO.getPrice());
        }
        if (itemRequestDTO.getEffect() != null) {
            newItem.setEffect(itemRequestDTO.getEffect());
        }
        return itemRepository.save(newItem);
    }

    public Item update(Long id, ItemRequestDTO itemRequestDTO) {
        Optional<Item> optionalItem = getItem(id);
        Item itemFromDb = optionalItem.get();
        validateUpdatedName(itemFromDb, itemRequestDTO.getName());
        itemFromDb.setName(itemRequestDTO.getName());
        itemFromDb.setQuantity(itemRequestDTO.getQuantity());
        itemFromDb.setDescription(itemRequestDTO.getDescription());
        itemFromDb.setWeight(itemRequestDTO.getWeight());
        itemFromDb.setPrice(itemRequestDTO.getPrice());
        itemFromDb.setEffect(itemRequestDTO.getEffect());
        return itemRepository.save(itemFromDb);
    }

    public Item updateName(Long id, ItemRequestDTO itemRequestDTO) {
        Optional<Item> optionalItem = getItem(id);
        Item itemFromDb = optionalItem.get();
        validateUpdatedName(itemFromDb, itemRequestDTO.getName());
        itemFromDb.setName(itemRequestDTO.getName());
        return itemRepository.save(itemFromDb);
    }

    public void delete(Long id) {
        boolean existsById = itemRepository.existsById(id);
        if (!existsById) { throw new ItemNotFoundException(id.toString()); }
        else { itemRepository.deleteById(id); }
    }

    public List<Item> getAll() { return itemRepository.findAll(); }

    public Item getById(Long id) {
        Optional<Item> optionalItem = getItem(id);
        return optionalItem.get();
    }

    public List<Item> getByName(String name) {
        return itemRepository.findByName(name);
    }

    private Optional<Item> getItem(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(id.toString());
        }
        return optionalItem;
    }

    private void validateName(String name) {
        boolean exists = itemRepository.existsByName(name);
        if (exists) {
            throw new BadRequestException("Item with name " + name + " already exists.");
        }
    }

    private void validateUpdatedName(Item itemFromDb, String dto) {
        if (!itemFromDb.getName().equalsIgnoreCase(dto)) { validateName(dto); }
    }
}
