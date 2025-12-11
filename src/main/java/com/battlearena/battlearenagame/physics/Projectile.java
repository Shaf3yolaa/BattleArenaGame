package com.battlearena.battlearenagame.physics;
import javafx.scene.shape.Circle;

public class Projectile {
    private  double x;
    private double y;
    private double speed;
    private int damage;
    public boolean active = true;
    private Circle view;
    private boolean movingRight;


    public Projectile(double x, double y, double speed, int damage, boolean movingRight,double radius) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.movingRight = movingRight;
        this.view = new Circle(x, y, radius);
    }

    public double getX() {
        return x;
    }

    public double getY(){return y;}

    public int getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public Circle getView() {
        return view;
    }

    public boolean movingRight() {
        return movingRight;
    }


    public void move() {
      if (!active) {
       return;
        }

    if (movingRight) {

      x = x + speed;
    }
    else {
      x = x - speed;
        }
        view.setCenterX(x);
    }
}


