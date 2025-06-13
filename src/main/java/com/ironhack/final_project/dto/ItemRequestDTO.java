package com.ironhack.final_project.dto;

import com.ironhack.final_project.model.Effect;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {
    @NotBlank
    private String name;

    private int quantity;

    private String description;

    private double weight;

    private int price;

    private List<Effect> effect;
}
