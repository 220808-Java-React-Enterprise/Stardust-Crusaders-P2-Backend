package com.revature.pokecare.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "move_id")
public class Move {
    @Id
    private int move_id;

    @Column(name = "name")
    private String name;

    @Column(name = "attack_type")
    private String move_type;

    @Column(name = "damage_class")
    private String damage_class;

    @Column(name = "accuracy")
    private int accuracy;

    @Column(name = "power")
    private int power;

    @Column(name = "pp")
    private int pp;

    public Move() {
    }

    public Move(int move_id, String name, String move_type, String damage_class, int accuracy, int power, int pp) {
        this.move_id = move_id;
        this.name = name;
        this.move_type = move_type;
        this.damage_class = damage_class;
        this.accuracy = accuracy;
        this.power = power;
        this.pp = pp;
    }

    public int getMove_id() {
        return move_id;
    }

    public void setMove_id(int move_id) {
        this.move_id = move_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMove_type() {
        return move_type;
    }

    public void setMove_type(String move_type) {
        this.move_type = move_type;
    }

    public String getDamage_class() {
        return damage_class;
    }

    public void setDamage_class(String damage_class) {
        this.damage_class = damage_class;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    @Override
    public String toString() {
        return "Move{" +
                "move_id=" + move_id +
                ", name='" + name + '\'' +
                ", move_type='" + move_type + '\'' +
                ", damage_class='" + damage_class + '\'' +
                ", accuracy=" + accuracy +
                ", power=" + power +
                ", pp=" + pp +
                '}';
    }
}
