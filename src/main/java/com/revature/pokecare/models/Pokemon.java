package com.revature.pokecare.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;

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

    @Column(name = "in_daycare")
    private boolean in_daycare;

    @Column(name = "enroll_date")
    private Date enroll_date;

    @OneToOne
    @JoinColumn(name = "ev_id", nullable = false)
    @JsonBackReference
    private EVs evs;

    @OneToOne
    @JoinColumn(name = "iv_id", nullable = false)
    @JsonBackReference
    private IVs ivs;

    @OneToOne
    @JoinColumn(name = "move_id", nullable = false)
    @JsonBackReference
    private MoveSet move_set;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    /*@OneToOne (
            mappedBy = "pokemon",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    ) */


    public Pokemon() {
    }


    public Pokemon(String pokemon_id, String name, int pokedex_id, int level, int xp_needed, String ability, String nature, boolean in_daycare, Date enroll_date, EVs evs, IVs ivs, MoveSet move_set, User user) {
        this.pokemon_id = pokemon_id;
        this.name = name;
        this.pokedex_id = pokedex_id;
        this.level = level;
        this.xp_needed = xp_needed;
        this.ability = ability;
        this.nature = nature;
        this.in_daycare = in_daycare;
        this.enroll_date = enroll_date;
        this.evs = evs;
        this.ivs = ivs;
        this.move_set = move_set;
        this.user = user;
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

    public boolean isIn_daycare() {
        return in_daycare;
    }

    public void setIn_daycare(boolean in_daycare) {
        this.in_daycare = in_daycare;
    }

    public Date getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(Date enroll_date) {
        this.enroll_date = enroll_date;
    }

    public EVs getEvs() {
        return evs;
    }

    public void setEvs(EVs evs) {
        this.evs = evs;
    }

    public IVs getIvs() {
        return ivs;
    }

    public void setIvs(IVs ivs) {
        this.ivs = ivs;
    }

    public MoveSet getMove_set() {
        return move_set;
    }

    public void setMove_set(MoveSet move_set) {
        this.move_set = move_set;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MoveSet getMoveset() {
        return move_set;
    }

    public void setMoveset(MoveSet move_set) {
        this.move_set = move_set;
    }

    public User getUser() {
        return user;
    }

    public void setUser_id(User user) {
        this.user = user;
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
                ", in_daycare=" + in_daycare +
                ", evs=" + evs +
                ", ivs=" + ivs +
                ", move_set=" + move_set +
                ", user=" + user +
                '}';
    }
}
