package com.revature.pokecare.repositories;

import com.revature.pokecare.models.Pokemon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, String> {

    @Query(value = "SELECT * FROM pokemon WHERE id = ?1", nativeQuery = true)
    Pokemon findByID(String id);

    @Query(value = "SELECT * FROM pokemon WHERE user_id = ?1", nativeQuery = true)
    List<String> findByUserID(String user_id);

    @Query(value = "SELECT * FROM pokemon WHERE user_id = ?1 AND in_daycare = true", nativeQuery = true)
    List<String> getFromDaycare(String user_id);

    @Query(value = "SELECT hpEV FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    int findHPEV(String pokemon_id);

    @Query(value = "SELECT hpIV FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    int findHPIV(String pokemon_id);

    @Query(value = "SELECT level FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    int findLevel(String pokemon_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pokemon SET in_daycare = ?2 WHERE pokemon_id = ?1", nativeQuery = true)
    void setDaycareStatus(String id, boolean isActive);

}
