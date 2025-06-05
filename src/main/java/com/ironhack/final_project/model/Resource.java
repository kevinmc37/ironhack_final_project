package com.ironhack.final_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends Item {
    private List<Item> recipe;

    public Item combineResource(List<Resource> resources) {
        return null;
    }
}
