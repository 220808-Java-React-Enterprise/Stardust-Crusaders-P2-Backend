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

    @Column(name = "xp_needed", nullable = false)
    private int xp_needed;

    @Column(name = "ability", nullable = false)
    private String ability;

    @Column(name = "nature", nullable = false)
    private String nature;

    @Column(name = "hpIV", nullable = false)
    private int hpIV;

    @Column(name = "hpEV", nullable = false)
    private int hpEV;

    @Column(name = "attack", nullable = false)
    private int attack;

    @Column(name = "special_attack", nullable = false)
    private int special_attack;

    @Column(name = "defense", nullable = false)
    private int defense;

    @Column(name = "special_defense", nullable = false)
    private int special_defense;

    @Column(name = "speed", nullable = false)
    private int speed;

    @Column(name = "daycare_id", nullable = false)
    private String daycare_id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    public Pokemon() {
    }

    public Pokemon(String pokemon_id, String name, int pokedex_id, int level, int xp_needed, String ability, String nature, int hpIV, int hpEV, int attack, int special_attack, int defense, int special_defense, int speed, String daycare_id, String user_id) {
        this.pokemon_id = pokemon_id;
        this.name = name;
        this.pokedex_id = pokedex_id;
        this.level = level;
        this.xp_needed = xp_needed;
        this.ability = ability;
        this.nature = nature;
        this.hpIV = hpIV;
        this.hpEV = hpEV;
        this.attack = attack;
        this.special_attack = special_attack;
        this.defense = defense;
        this.special_defense = special_defense;
        this.speed = speed;
        this.daycare_id = daycare_id;
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

    public int getHpIV() {
        return hpIV;
    }

    public void setHpIV(int hpIV) {
        this.hpIV = hpIV;
    }

    public int getHpEV() {
        return hpEV;
    }

    public void setHpEV(int hpEV) {
        this.hpEV = hpEV;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpecial_attack() {
        return special_attack;
    }

    public void setSpecial_attack(int special_attack) {
        this.special_attack = special_attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecial_defense() {
        return special_defense;
    }

    public void setSpecial_defense(int special_defense) {
        this.special_defense = special_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDaycare_id() {
        return daycare_id;
    }

    public void setDaycare_id(String daycare_id) {
        this.daycare_id = daycare_id;
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
                ", hpIV=" + hpIV +
                ", hpEV=" + hpEV +
                ", attack=" + attack +
                ", special_attack=" + special_attack +
                ", defense=" + defense +
                ", special_defense=" + special_defense +
                ", speed=" + speed +
                ", daycare_id='" + daycare_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
