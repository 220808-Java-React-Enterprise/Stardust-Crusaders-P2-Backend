package com.revature.pokecare.dtos.requests;

public class NewPokemonRequest {

    private String name;
    private int pokedex_id;
    private String user_id;

    public NewPokemonRequest(String name, int pokedex_id, String user_id) {
        this.name = name;
        this.pokedex_id = pokedex_id;
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPokedex_id() {
        return pokedex_id;
    }

    public void setPokedex_id(int pokedex_id) {
        this.pokedex_id = pokedex_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
