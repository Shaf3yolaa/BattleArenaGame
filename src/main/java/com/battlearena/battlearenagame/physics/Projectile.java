package com.battlearena.battlearenagame.physics;
import javafx.scene.shape.Circle;

public class Projectile {
    private  double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private int damage;
    public boolean active = true;
    private Circle view;


    public Projectile(double x, double y, double speed, int damage, double angleInDegrees,double radius) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.view = new Circle(x, y, radius);
        double radians = Math.toRadians(angleInDegrees);
        this.velocityX = Math.cos(radians) * speed;
        this.velocityY = Math.sin(radians) * speed;
    }

    public double getX() {
        return x;
    }

    public double getY(){return y;}

    public int getDamage() {
        return damage;
    }

    public Circle getView() {
        return view;
    }

    public void move() {
        if (!active) return;
        x = x+velocityX;
        y = y+velocityY;

        view.setCenterX(x);
        view.setCenterY(y);
    }
}


