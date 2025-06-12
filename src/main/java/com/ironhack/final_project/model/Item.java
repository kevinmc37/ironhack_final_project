package com.ironhack.final_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotBlank
    private String name;

    @Positive
    private int quantity;

    @Positive
    private int price;

    @PositiveOrZero
    private double weight;

    @NotBlank
    private String description;

    @ManyToMany
    private List<Effect> effect;

    public void setAdditionalDescription() {
        String description = "\nCantidad: " + this.getQuantity() +
                "\nPrecio: " + this.getPrice() + "\nPeso: " + this.getWeight();
        this.setDescription(this.getDescription() + description);
    }

    public void useItem(Player player) {

    }
}