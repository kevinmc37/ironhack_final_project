package com.ironhack.final_project.exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String param) {
        super("Inventory with id, player or items " + param + " not found.");
    }
}
