package com.revature.pokecare.models;

import javax.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    private String pokemon_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pokedex_id", nullable = false)
    private int pokedex_id;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "xp_needed")
    private int xp_needed;

    @Column(name = "ability", nullable = false)
    private String ability;

    @Column(name = "nature", nullable = false)
    private String nature;


    @Column(name = "ev_id", nullable = false)
    private String ev_id;

    @Column(name = "iv_id", nullable = false)
    private String iv_id;

    @Column(name = "move_id", nullable = false)
    private String move_id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    public Pokemon() {
    }

    public Pokemon(String pokemon_id, String name, int pokedex_id, int level, int xp_needed, String ability, String nature, String ev_id, String iv_id, String move_id, String user_id) {
        this.pokemon_id = pokemon_id;
        this.name = name;
        this.pokedex_id = pokedex_id;
        this.level = level;
        this.xp_needed = xp_needed;
        this.ability = ability;
        this.nature = nature;
        this.ev_id = ev_id;
        this.iv_id = iv_id;
        this.move_id = move_id;
        this.user_id = user_id;
    }

    public String getPokemon_id() {
        return pokemon_id;
    }

    public void setPokemon_id(String pokemon_id) {
        this.pokemon_id = pokemon_id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp_needed() {
        return xp_needed;
    }

    public void setXp_needed(int xp_needed) {
        this.xp_needed = xp_needed;
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

    public String getEv_id() {
        return ev_id;
    }

    public void setEv_id(String ev_id) {
        this.ev_id = ev_id;
    }

    public String getIv_id() {
        return iv_id;
    }

    public void setIv_id(String iv_id) {
        this.iv_id = iv_id;
    }

    public String getMove_id() {
        return move_id;
    }

    public void setMove_id(String move_id) {
        this.move_id = move_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemon_id='" + pokemon_id + '\'' +
                ", name='" + name + '\'' +
                ", pokedex_id=" + pokedex_id +
                ", level=" + level +
                ", xp_needed=" + xp_needed +
                ", ability='" + ability + '\'' +
                ", nature='" + nature + '\'' +
                ", ev_id='" + ev_id + '\'' +
                ", iv_id='" + iv_id + '\'' +
                ", move_id='" + move_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
