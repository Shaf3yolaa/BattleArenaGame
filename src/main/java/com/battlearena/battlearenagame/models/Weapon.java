package com.battlearena.battlearenagame.models;

public abstract class Weapon {
    private String name;
    private int damage;
    private int speed;
    private long coolDownTime;

    public Weapon(String name,int damage, int speed, long coolDownTime) {
        this.name = name;
        this.damage = damage;
        this.speed = speed;
        this.coolDownTime = coolDownTime;
    }

    public String getName(){
        return name;
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
}
