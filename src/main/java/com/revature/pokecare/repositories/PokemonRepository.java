package com.revature.pokecare.repositories;

import com.revature.pokecare.models.Pokemon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, String> {

    @Query(value = "SELECT name FROM pokemon WHERE name = ?1", nativeQuery = true)
    String findByName(String name);

}
