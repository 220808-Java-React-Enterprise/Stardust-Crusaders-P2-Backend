package com.revature.pokecare.dtos.requests;

public class PokemonIDRequest {

    private String pokemon_id;

    public PokemonIDRequest() {
    }

    public PokemonIDRequest(String pokemon_id) {
        this.pokemon_id = pokemon_id;
    }

    public String getPokemon_id() {
        return pokemon_id;
    }

    public void setPokemon_id(String pokemon_id) {
        this.pokemon_id = pokemon_id;
    }

    @Override
    public String toString() {
        return "PokemonIDRequest{" +
                "pokemon_id='" + pokemon_id + '\'' +
                '}';
    }
}
