package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.dtos.responses.ViewPokemon;
import com.revature.pokecare.models.EVs;
import com.revature.pokecare.models.IVs;
import com.revature.pokecare.models.MoveSet;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;
    private final EVRepository evRepository;
    private final IVRepository ivRepository;
    private final MoveSetRepository moveSetRepository;
    private final UserRepository userRepository;

    public PokemonService(PokemonRepository pokemonRepository, EVRepository evRepository, IVRepository ivRepository, MoveSetRepository moveSetRepository, UserRepository userRepository) {
        this.pokemonRepository = pokemonRepository;
        this.evRepository = evRepository;
        this.ivRepository = ivRepository;
        this.moveSetRepository = moveSetRepository;
        this.userRepository = userRepository;
    }

    public Pokemon findByID(String id) {
        return pokemonRepository.findByID(id);
    }

    public List<String> findByUserID(String user_id) {
        return pokemonRepository.findByUserID(user_id);
    }

    public List<ViewPokemon> getByUserId(String user_id) {
        List<ViewPokemon> result = new ArrayList<>();
        List<String> mons = pokemonRepository.findByUserID(user_id);
        for (String s : mons) {
            String[] arr = s.split(",");
            try {
                ViewPokemon mon = new ViewPokemon(arr[0], arr[1], Boolean.parseBoolean(arr[2]), Integer.parseInt(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6]), Integer.parseInt(arr[7]), arr[8], arr[9], arr[10], arr[11]);
                result.add(mon);
            } catch (InputMismatchException e) {
                e.getStackTrace();
                throw new InputMismatchException();
            }
        }
        return result;
    }


    public Pokemon save(NewPokemonRequest newPokemonRequest) {
        int level = 1;
        int xp_needed = 0;
        Pokemon pokemon = new Pokemon(UUID.randomUUID().toString(), newPokemonRequest.getName(), newPokemonRequest.getPokedex_id(), level, xp_needed, newPokemonRequest.getAbility(), newPokemonRequest.getNature(), false, new EVs(UUID.randomUUID().toString()), new IVs(UUID.randomUUID().toString()), new MoveSet(UUID.randomUUID().toString()), userRepository.findById(newPokemonRequest.getUser_id()).get());
        evRepository.save(pokemon.getEvs());
        ivRepository.save(pokemon.getIvs());
        moveSetRepository.save(pokemon.getMoveset());
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