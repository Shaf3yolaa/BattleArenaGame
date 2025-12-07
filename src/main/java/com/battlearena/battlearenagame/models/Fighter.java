package com.battlearena.battlearenagame.models;

import javafx.scene.shape.Rectangle;

public abstract class Fighter {

    protected String name;
    protected double x, y;
    protected int health;
    protected double speed;
    protected Weapon weapon;
    protected long lastShotcdt = 0;
    protected Rectangle view;

    public Fighter(String name, double x, double y, int health, double speed, Weapon weapon) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = health;
        this.speed = speed;
        this.weapon = weapon;
        this.view = new Rectangle(40, 40);
        this.view.setX(x);
        this.view.setY(y);
    }

    //-------------------------------------------------------------------------------shot & damage
    public void takeDamage(int dmg) {
        this.health -= dmg;
        if (this.health < 0) this.health = 0;
    }
    public abstract void shoot();

    public boolean canShoot(long currentTime) {
        if (currentTime - lastShotcdt >= weapon.getCoolDownTime()) {
            lastShotcdt = currentTime;
            return true;
        }
        return false;
    }
    public void recordShotTime(long currentTime) {
        this.lastShotcdt = currentTime;
    }


    public boolean isDead() {
        return health <= 0;
    }

    // ---------------------------------------------------------------------Getters and Setters
    public String getName() { return name; }
    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) {
        this.x = x;
        this.view.setX(x);
    }
    public void setY(double y) {
        this.y = y;
        this.view.setY(y);
    }

    public Rectangle getView() { return view; }
    public int getHealth() { return health; }
    public Weapon getWeapon() { return weapon; }
    public double getSpeed() { return speed; }
}