package com.revature.pokecare.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "move_set")
public class MoveSet {

    @Id
    private String move_id;

    @Column(name = "move1")
    private String move1;

    @Column(name = "move2")
    private String move2;

    @Column(name = "move3")
    private String move3;

    @Column(name = "move4")
    private String move4;

    @OneToOne (
            mappedBy = "move_set",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private Pokemon pokemon;

    public MoveSet() {
    }

    public MoveSet(String move_id) {
        this.move_id = move_id;
    }

    public MoveSet(String move_id, String move1, String move2, String move3, String move4) {
        this.move_id = move_id;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
    }

    public String getMove_id() {
        return move_id;
    }

    public void setMove_id(String move_id) {
        this.move_id = move_id;
    }

    public String getMove1() {
        return move1;
    }

    public void setMove1(String move1) {
        this.move1 = move1;
    }

    public String getMove2() {
        return move2;
    }

    public void setMove2(String move2) {
        this.move2 = move2;
    }

    public String getMove3() {
        return move3;
    }

    public void setMove3(String move3) {
        this.move3 = move3;
    }

    public String getMove4() {
        return move4;
    }

    public void setMove4(String move4) {
        this.move4 = move4;
    }

    @Override
    public String toString() {
        return "MoveSet{" +
                "move_id='" + move_id + '\'' +
                ", move1='" + move1 + '\'' +
                ", move2='" + move2 + '\'' +
                ", move3='" + move3 + '\'' +
                ", move4='" + move4 + '\'' +
                '}';
    }
}
