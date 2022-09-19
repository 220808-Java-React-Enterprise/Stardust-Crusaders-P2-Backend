package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.models.User;
import com.revature.pokecare.repositories.PokemonRepository;
import com.revature.pokecare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepo;

    public PokemonService(PokemonRepository pokemonRepository, UserRepository userRepo) {
        this.pokemonRepository = pokemonRepository;
        this.userRepo = userRepo;
    }

    public Pokemon findByID(String id) {
        return pokemonRepository.findByID(id);
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
        User user = userRepo.findById(newPokemonRequest.getUser_id()).get();
        Pokemon pokemon = new Pokemon(UUID.randomUUID().toString(), newPokemonRequest.getName(), newPokemonRequest.getPokedex_id(), level, xp_needed, ability, nature, hpIV, hpEV, attack, special_attack, defense, special_defense, speed, daycare_id, user);
        pokemonRepository.save(pokemon);
        return pokemon;
    }

    public int getHP(String id) {
        int hpIV = pokemonRepository.findHPIV(id);
        int hpEV = pokemonRepository.findHPEV(id);
        int level = pokemonRepository.findLevel(id);

        int base = 1;
        int hp = ((2*base + hpIV + (hpEV/4)*level)/100) + level + 10;
        return hp;

    }

}
