package com.ironhack.final_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long player_id;

    @NotBlank
    private String player_name;

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
}
