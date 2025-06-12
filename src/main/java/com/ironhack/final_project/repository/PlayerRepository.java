package com.ironhack.final_project.repository;

import com.ironhack.final_project.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {}
