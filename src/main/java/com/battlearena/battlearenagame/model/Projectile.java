package com.battlearena.battlearenagame.model;

public class Projectile {
    private int damage;
    private int speed;
    private String direction;

    public  Projectile(int damage,int speed,String direction)
    {
        this.damage=damage;
        this.speed=speed;
        this.direction=direction;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void projectileMovement(){
        System.out.println("Projectile movement"+ direction +"with speed"+ speed);
    }
}
