package com.ironhack.final_project.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String param) {
        super("Item with id or name " + param + " not found.");
    }
}
