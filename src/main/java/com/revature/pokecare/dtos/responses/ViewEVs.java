package com.revature.pokecare.dtos.responses;

public class ViewEVs {
    private int hitpoints;
    private int attack;
    private int defense;
    private int sp_attack;
    private int sp_defense;
    private int speed;

    public ViewEVs() {
    }

    public ViewEVs(int hitpoints, int attack, int defense, int sp_attack, int sp_defense, int speed) {
        this.hitpoints = hitpoints;
        this.attack = attack;
        this.defense = defense;
        this.sp_attack = sp_attack;
        this.sp_defense = sp_defense;
        this.speed = speed;
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
        return "ViewEVs{" +
                "hitpoints=" + hitpoints +
                ", attack=" + attack +
                ", defense=" + defense +
                ", sp_attack=" + sp_attack +
                ", sp_defense=" + sp_defense +
                ", speed=" + speed +
                '}';
    }
}
