package com.ironhack.final_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @NotBlank
    private String name;

    @Positive
    private int health;

    @Positive
    private int attack;

    @Positive
    private int defense;

    @Positive
    private int speed;

    @OneToOne
    private Inventory inventory;

    @PositiveOrZero
    private long gold;

    @PositiveOrZero
    private double weight;

    @ManyToMany
    private List<Effect> status = new ArrayList<>();
}
