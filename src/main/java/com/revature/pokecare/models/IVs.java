package com.revature.pokecare.models;

import javax.persistence.*;

@Entity
@Table(name = "IVs")
public class IVs {
    @Id
    private String iv_id;

    @Column(name = "hitpoints", nullable = false)
    private int hitpoints;

    @Column(name = "attack", nullable = false)
    private int attack;

    @Column(name = "defense", nullable = false)
    private int defense;

    @Column(name = "sp_attack", nullable = false)
    private int sp_attack;

    @Column(name = "sp_defense", nullable = false)
    private int sp_defense;

    @Column(name = "speed", nullable = false)
    private int speed;

    public IVs(){
    }

    public IVs(String iv_id) {
        this.iv_id = iv_id;
    }
    public IVs(String iv_id, int hitpoints, int attack, int defense, int sp_attack, int sp_defense, int speed) {
        this.iv_id = iv_id;
        this.hitpoints = hitpoints;
        this.attack = attack;
        this.defense = defense;
        this.sp_attack = sp_attack;
        this.sp_defense = sp_defense;
        this.speed = speed;
    }

    public String getIv_id() {
        return iv_id;
    }

    public void setIv_id(String iv_id) {
        this.iv_id = iv_id;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSp_attack() {
        return sp_attack;
    }

    public void setSp_attack(int sp_attack) {
        this.sp_attack = sp_attack;
    }

    public int getSp_defense() {
        return sp_defense;
    }

    public void setSp_defense(int sp_defense) {
        this.sp_defense = sp_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "IVs{" +
                "iv_id='" + iv_id + '\'' +
                ", hitpoints=" + hitpoints +
                ", attack=" + attack +
                ", defense=" + defense +
                ", sp_attack=" + sp_attack +
                ", sp_defense=" + sp_defense +
                ", speed=" + speed +
                '}';
    }
}
