package com.battlearena.battlearenagame.model;

public class Cannon extends Weapon{
    public Cannon(){
        super(45,12,2000);
    }
    @Override
    public Projectile newProjectile(String direction)
    {
        return new Projectile(damage,speed,direction);
    }
}
