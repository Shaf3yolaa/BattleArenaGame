package com.battlearena.battlearenagame.models;

import javafx.scene.shape.Rectangle;

public abstract class Fighter {

    protected double x, y;
    protected int health;
    protected double speed;
    protected Weapon weapon;

    protected int MAXHealth;
    protected long lastShotTime = 0;
    protected Rectangle view;

    public Fighter(double x, double y, int health, double speed, Weapon weapon) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.speed = speed;
        this.weapon = weapon;

        this.MAXHealth = health;
        this.view = new Rectangle(40, 40);
        this.view.setX(x);
        this.view.setY(y);
    }


    public void moveup() {
        setY(this.y - this.speed);
    }

    public void movedown() {
        setY(this.y + this.speed);
    }

    public void moveright() {
        setX(this.x + this.speed);
    }

    public void moveleft() {
        setX(this.x - this.speed);
    }

    public void takeDamage(int dmg) {
        this.health -= dmg;
        if (this.health < 0) this.health = 0;
    }
    public boolean canShoot(long currentTime) {
        if (currentTime - lastShotTime >= weapon.getCoolDownTime()) {
            lastShotTime = currentTime;
            return true;
        }
        return false;
    }

    public boolean isDead() {
        return health <= 0;
    }

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
    public int getMaxHealth() { return MAXHealth; }
    public Weapon getWeapon() { return weapon; }
    public double getSpeed() { return speed; }
}