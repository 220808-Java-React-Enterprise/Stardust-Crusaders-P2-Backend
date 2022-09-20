package com.revature.pokecare.controllers;


import com.revature.pokecare.dtos.requests.NewPokemonRequest;
import com.revature.pokecare.dtos.requests.PokemonIDRequest;
import com.revature.pokecare.models.Pokemon;
import com.revature.pokecare.services.PokemonService;
import com.revature.pokecare.services.TokenService;
import com.revature.pokecare.utils.custom_exceptions.InvalidRequestException;
import com.revature.pokecare.utils.custom_exceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private final PokemonService pokemonService;
    private final TokenService tokenService;

    public PokemonController(PokemonService pokemonService, TokenService tokenService) {
        this.pokemonService = pokemonService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @ExceptionHandler(value = {ResourceConflictException.class, InvalidRequestException.class})
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/save", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String savePokemon(@RequestBody NewPokemonRequest newPokemonRequest, HttpServletRequest req) {
        newPokemonRequest.setUser_id(tokenService.extractRequesterDetails(req.getHeader("Authorization")).getId());

        return pokemonService.save(newPokemonRequest).getPokemon_id();
    }

    @CrossOrigin
    @GetMapping(value = "/gethp", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody int getHP(@RequestBody PokemonIDRequest pokemonIDRequest) {

        int hp = pokemonService.getHP(pokemonIDRequest.getPokemon_id());

        return hp;
    }


    @CrossOrigin
    @GetMapping(value = "/viewall", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Pokemon[] viewAllPokemon(HttpServletRequest req) {

        return pokemonService.findByUserID(tokenService.extractRequesterDetails(req.getHeader("Authorization")).getId());
    }

}
