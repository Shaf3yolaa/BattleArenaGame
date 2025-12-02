package com.battlearena.battlearenagame.model;

public class Pistol extends Weapon{
    public Pistol(){
        super(12,30,500);
    }
    @Override
    public Projectile newProjectile(String direction)
    {
        return new Projectile(damage,speed,direction);
    }
}
