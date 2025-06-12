package com.ironhack.final_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @Positive
    private int space;

    @OneToMany
    private List<Item> items;

    @OneToOne
    private Player player;

    public void order() {

    }

    public double getWeight() {
        double weight = 0;
        for (Item item : items) {
            weight += item.getWeight();
        }
        return weight;
    }
}
