package com.revature.pokecare.repositories;

import com.revature.pokecare.dtos.responses.ViewPokemon;
import com.revature.pokecare.models.Pokemon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, String> {

    @Query(value = "SELECT * FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    String findByID(String id);

    @Query(value = "SELECT * FROM pokemon WHERE user_id = ?1", nativeQuery = true)
    List<String> findByUserID(String user_id);

    @Query(value = "SELECT * FROM pokemon WHERE user_id = ?1 AND in_daycare = true", nativeQuery = true)
    List<String> getFromDaycare(String user_id);

    @Query(value = "SELECT level FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    int findLevel(String pokemon_id);

    @Query(value = "SELECT pokemon_id FROM pokemon WHERE user_id = ?1", nativeQuery = true)
    String[] getIDs(String user_id);

    @Query(value = "SELECT xp_needed FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    int getXP(String pokemon_id);

    @Query(value = "SELECT enroll_date FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    Timestamp getDateEnrolled(String pokemon_id);

    @Query(value = "SELECT name FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    String getName(String pokemon_id);

    @Query(value = "SELECT pokedex_id FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    String getPokedexID(String pokemon_id);

    @Query(value = "SELECT ability FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    String getAbility(String pokemon_id);

    @Query(value = "SELECT nature FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    String getNature(String pokemon_id);

    @Query(value = "SELECT level FROM pokemon WHERE user_id = ?1", nativeQuery = true)
    Integer[] pokeLevelsFromUserID(String user_id);

    @Query(value = "SELECT ev_id FROM pokemon WHERE pokemon_id = ?1", nativeQuery = true)
    String getEVID(String pokemon_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pokemon SET enroll_date = ?1 WHERE pokemon_id = ?2", nativeQuery = true)
    void enrollPoke(Timestamp date, String pokemon_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pokemon SET in_daycare = ?2 WHERE pokemon_id = ?1", nativeQuery = true)
    void setDaycareStatus(String id, boolean isActive);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pokemon SET level = ?2 WHERE pokemon_id = ?1", nativeQuery = true)
    void setNewLevel(String id, int newLevel);

    @Transactional
    @Modifying
    @Query(value = "UPDATE pokemon SET xp_needed = ?2 WHERE pokemon_id = ?1", nativeQuery = true)
    void setNewXP(String id, int newXP);

}
