package com.revature.pokecare.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "pokemon_moves")
public class PokemonMove {
    @Id
    private String pokemon_move_id;

    @ManyToOne
    @JoinColumn (name = "pokemon_id", nullable = false)
    @JsonBackReference
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn (name = "move_id")
    @JsonBackReference
    private Move move;


}
