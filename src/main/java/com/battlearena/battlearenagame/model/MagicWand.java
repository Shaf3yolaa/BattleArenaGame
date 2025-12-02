package com.battlearena.battlearenagame.model;

public class MagicWand extends Weapon{
    public MagicWand(){
        super(28,40,1200);
    }
    @Override
    public Projectile newProjectile(String direction){
        return new Projectile(damage,speed,direction);
    }
}
