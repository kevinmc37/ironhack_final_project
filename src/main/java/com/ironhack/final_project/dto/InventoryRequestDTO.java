package com.ironhack.final_project.dto;

import com.ironhack.final_project.model.Item;
import com.ironhack.final_project.model.Player;
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
public class InventoryRequestDTO {
    private int space;

    @NotNull
    private Player player;

    @NotNull
    private List<Item> items;
}
