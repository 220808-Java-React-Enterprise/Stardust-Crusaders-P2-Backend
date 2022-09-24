package com.revature.pokecare.repositories;

import com.revature.pokecare.dtos.responses.ViewEVs;
import com.revature.pokecare.models.EVs;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface EVRepository extends CrudRepository<EVs, String> {
    @Query(value = "SELECT * FROM evs WHERE ev_id = ?1", nativeQuery = true)
    String[] getEV(String ev_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE evs SET hitpoints = ?1, attack = ?2, defense = ?3, sp_attack = ?4, sp_defense = ?5, speed = ?6 WHERE ev_id = ?7", nativeQuery = true)
    void setEV(int hitpoints, int attack, int defense, int sp_attack, int sp_defense, int speed, String ev_id);
}
