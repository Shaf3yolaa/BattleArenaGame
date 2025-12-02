package com.battlearena.battlearenagame.model;

public abstract class Fighter {

    protected String name;
    protected double health;
    protected double speed;
    protected double x;
    protected double y;

    //class weapon
    protected Weapon isweapon;
    protected boolean willshoot = true;

    public Fighter(String name, double health, double speed) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.x = 0;
        this.y = 0;
    }

    //move of fighter
    public void moveup() {
        y -= speed;
    }

    public void movedown() {
        y += speed;
    }

    public void moveright() {
        x += speed;
    }

    public void moveleft() {
        x -= speed;
    }


    public double shoot() {


    }

    public void takedamage(double damage) {
        health -= damage;
        if (health < 0)
            health = 0;
    }

    public boolean dead() {

        return health == 0;
    }

    //getters
    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Weapon getIsweapon() {
        return isweapon;
    }


}
