package com.ironhack.final_project.repository;

import com.ironhack.final_project.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);
    /*List<Coffee> findByStrength(int strength);
    List<Coffee> findByStrengthGreaterThan(int strength);
    List<Coffee> findByOriginAndName(String origin, String name);
    List<Coffee> findByName(String name);
    @Query("SELECT c FROM Coffee c WHERE c.origin = ?1 ORDER BY c.strength DESC")
    List<Coffee> findStrongestByOrigin(String origin);
    @Query(value = "SELECT * FROM coffee WHERE LENGTH(name) > :minLength", nativeQuery = true)
    List<Coffee> findByNameLengthGreaterThan(@Param("minLength") int minLength);

    boolean existsByName(String name);*/
}
