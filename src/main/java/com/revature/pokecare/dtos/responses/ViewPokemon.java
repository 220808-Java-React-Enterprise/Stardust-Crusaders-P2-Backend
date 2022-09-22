package com.revature.pokecare.dtos.responses;

import com.revature.pokecare.models.EVs;
import com.revature.pokecare.models.IVs;
import com.revature.pokecare.models.MoveSet;
import com.revature.pokecare.models.User;

import java.sql.Date;

public class ViewPokemon {

    private String pokemon_id;
    private String ability;

    private String enroll_date;
    private int level;
    private String name;
    private String nature;
    private int pokedex_id;
    private int xp_needed;
    private String ev_id;
    private String iv_id;
    private String move_id;
    private String user_id;

    public ViewPokemon(){
    }

    public ViewPokemon(String pokemon_id, String ability, String enroll_date, int level, String name, String nature, int pokedex_id, int xp_needed, String ev_id, String iv_id, String move_id, String user_id) {
        this.pokemon_id = pokemon_id;
        this.ability = ability;
        this.enroll_date = enroll_date;
        this.level = level;
        this.name = name;
        this.nature = nature;
        this.pokedex_id = pokedex_id;
        this.xp_needed = xp_needed;
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

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getPokedex_id() {
        return pokedex_id;
    }

    public void setPokedex_id(int pokedex_id) {
        this.pokedex_id = pokedex_id;
    }

    public int getXp_needed() {
        return xp_needed;
    }

    public void setXp_needed(int xp_needed) {
        this.xp_needed = xp_needed;
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

    public String getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(String enroll_date) {
        this.enroll_date = enroll_date;
    }

    @Override
    public String toString() {
        return "ViewPokemon{" +
                "pokemon_id='" + pokemon_id + '\'' +
                ", ability='" + ability + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", nature='" + nature + '\'' +
                ", pokedex_id=" + pokedex_id +
                ", xp_needed=" + xp_needed +
                ", ev_id='" + ev_id + '\'' +
                ", iv_id='" + iv_id + '\'' +
                ", move_id='" + move_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}