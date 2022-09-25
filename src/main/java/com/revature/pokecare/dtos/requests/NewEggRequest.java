package com.revature.pokecare.dtos.requests;

public class NewEggRequest {
    private String pokemon_id1;
    private String pokemon_id2;

    public NewEggRequest() {
    }

    public NewEggRequest(String pokemon_id1, String pokemon_id2) {
        this.pokemon_id1 = pokemon_id1;
        this.pokemon_id2 = pokemon_id2;
    }

    public String getPokemon_id1() {
        return pokemon_id1;
    }

    public void setPokemon_id1(String pokemon_id1) {
        this.pokemon_id1 = pokemon_id1;
    }

    public String getPokemon_id2() {
        return pokemon_id2;
    }

    public void setPokemon_id2(String pokemon_id2) {
        this.pokemon_id2 = pokemon_id2;
    }

    @Override
    public String toString() {
        return "NewEggRequest{" +
                "pokemon_id1='" + pokemon_id1 + '\'' +
                ", pokemon_id2='" + pokemon_id2 + '\'' +
                '}';
    }
}
