package com.revature.pokecare.dtos.requests;

public class NewPokemonRequest {

    private String name;
    private int pokedex_id;
    private String ability;
    private String nature;
    private String user_id;

    public NewPokemonRequest() {
    }

    public NewPokemonRequest(String name, int pokedex_id, String ability, String nature, String user_id) {
        this.name = name;
        this.pokedex_id = pokedex_id;
        this.ability = ability;
        this.nature = nature;
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

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
