package com.ironhack.final_project.model;

import com.ironhack.final_project.enums.Equipable;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment extends Item {
    private Equipable type;

    @PositiveOrZero
    private int maxHealth;

    @PositiveOrZero
    private int attack;

    @PositiveOrZero
    private int defense;

    @PositiveOrZero
    private int speed;

    public void setEquipmentDescription() {
        String description = "";
        if (this.getAttack() != 0) {
            description += "\nAtaque: " + this.getAttack();
        }
        if (this.getDefense() != 0) {
            description += "\nDefensa: " + this.getDefense();
        }
        if (this.getSpeed() != 0) {
            description += "\nVelocidad: " + this.getSpeed();
        }
        this.setDescription(this.getDescription() + description);
        this.setAdditionalDescription();
    }
}
