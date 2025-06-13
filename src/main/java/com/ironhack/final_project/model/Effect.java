package com.ironhack.final_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
public class Effect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long effectId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @PositiveOrZero
    private int turns;

    public String slowTarget(Player player, int speed_debuff, int turns) {
        if (turns == 0) {
            setTurns(turns);
            return player.getName() + "'s speed reduced by " + speed_debuff +
                    " for " + getTurns() + " seconds";
        }
        else {
            setTurns(0);
            return player.getName() + " is already slowed.";
        }
    }
}
