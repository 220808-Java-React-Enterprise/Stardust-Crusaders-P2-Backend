package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    public Pokemon save(NewPokemonRequest newPokemonRequest) {
        int level = 0;
        int xp_needed = 0;
        String ability = "none";
        String nature = "none";
        int attack = 0;
        int special_attack = 0;
        int defense = 0;
        int special_defense = 0;
        int speed = 0;
        String daycare_id = "none";

        int hpEV = 0;
        Random r = new Random();
        int hpIV = r.nextInt(32-1)+1;
        Pokemon pokemon = new Pokemon(UUID.randomUUID().toString(), newPokemonRequest.getName(), newPokemonRequest.getPokedex_id(), level, xp_needed, ability, nature, hpIV, hpEV, attack, special_attack, defense, special_defense, speed, daycare_id, newPokemonRequest.getUser_id());
        pokemonRepository.save(pokemon);
        return pokemon;
    }
}