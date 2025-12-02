package com.battlearena.battlearenagame.model;

public class Bow extends Weapon{
    public Bow(){
        super(20,35,1000);
    }
    @Override
    public Projectile newProjectile(String direction){
        return new Projectile(damage,speed,direction);
    }
}
