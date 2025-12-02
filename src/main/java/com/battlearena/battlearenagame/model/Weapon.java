package com.battlearena.battlearenagame.model;

public abstract class Weapon {
    protected int damage;
    protected int speed;
    protected long coolDownTime;

    public Weapon(int damage,int speed,long coolDownTime){
        this.damage=damage;
        this.speed=speed;
        this.coolDownTime=coolDownTime;
    }
    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }
    public long getCoolDownTime() {
        return coolDownTime;
    }

