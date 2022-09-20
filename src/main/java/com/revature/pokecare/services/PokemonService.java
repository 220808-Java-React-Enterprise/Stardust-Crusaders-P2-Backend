package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.models.EVs;
import com.revature.pokecare.models.IVs;
import com.revature.pokecare.models.MoveSet;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.repositories.EVRepository;
import com.revature.pokecare.repositories.IVRepository;
import com.revature.pokecare.repositories.MoveSetRepository;
import com.revature.pokecare.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;
    private final EVRepository evRepository;
    private final IVRepository ivRepository;
    private final MoveSetRepository moveSetRepository;

    public PokemonService(PokemonRepository pokemonRepository, EVRepository evRepository, IVRepository ivRepository, MoveSetRepository moveSetRepository) {
        this.pokemonRepository = pokemonRepository;
        this.evRepository = evRepository;
        this.ivRepository = ivRepository;
        this.moveSetRepository = moveSetRepository;
    }

    public Pokemon findByID(String id) {
        return pokemonRepository.findByID(id);
    }

    public Pokemon[] findByUserID(String user_id) {
        return pokemonRepository.findByUserID(user_id);
    }


    public Pokemon save(NewPokemonRequest newPokemonRequest) {
        int level = 1;
        int xp_needed = 0;
        Pokemon pokemon = new Pokemon(UUID.randomUUID().toString(), newPokemonRequest.getName(), newPokemonRequest.getPokedex_id(), level, xp_needed, newPokemonRequest.getAbility(), newPokemonRequest.getNature(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), newPokemonRequest.getUser_id());
        evRepository.save(new EVs(pokemon.getEv_id()));
        ivRepository.save(new IVs(pokemon.getIv_id()));
        moveSetRepository.save(new MoveSet(pokemon.getMove_id()));
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
