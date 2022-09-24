package com.revature.pokecare.services;

import com.revature.pokecare.dtos.requests.SetEVRequest;
import com.revature.pokecare.dtos.responses.ViewEVs;
import com.revature.pokecare.models.EVs;
import com.revature.pokecare.repositories.EVRepository;
import com.revature.pokecare.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.Arrays;

@Service
public class EVService {

    @Autowired
    private final EVRepository evRepository;
    private final PokemonRepository pokemonRepository;

    public EVService(EVRepository evRepository, PokemonRepository pokemonRepository) {
        this.evRepository = evRepository;
        this.pokemonRepository = pokemonRepository;
    }

    public void saveNewEV(EVs evs) {
        evRepository.save(evs);
    }

    public void setEVs(SetEVRequest setEVRequest) {
        String ev_id = pokemonRepository.getEVID(setEVRequest.getPokemon_id());
        System.out.println(ev_id);
        evRepository.setEV(setEVRequest.getHitpoints(), setEVRequest.getAttack(), setEVRequest.getDefense(), setEVRequest.getSp_attack(), setEVRequest.getSp_defense(), setEVRequest.getSpeed(), ev_id);
    }

    public String[] getEVs(String pokemon_id) {
        String ev_id = pokemonRepository.getEVID(pokemon_id);
        System.out.println(ev_id);
        return evRepository.getEV(ev_id);
    }
}
