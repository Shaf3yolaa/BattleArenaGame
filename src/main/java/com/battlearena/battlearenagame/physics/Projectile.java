package com.battlearena.battlearenagame.physics;
import javafx.scene.shape.Circle;

public class Projectile {
    private  double x;
    private double y;
    private double speed;
    private  double damage;

    public boolean active = true;
    private Circle view;
    private boolean movingRight;


    public Projectile(double x,double y, double speed, boolean movingRight,Circle view,double damage) {
        this.x = x;
        this.y = y;
        this.damage=damage;
        //complete
        this.speed = speed;
        this.view = new Circle(x, y,5);
        this.movingRight = movingRight;

    }

    public double getX() {
        return x;
    }

    public double getY(){return y;}

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public Circle getView() {
        return view;
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
    }


    public boolean checkCollision(Projectile bullet,Fighter Target){

        if (!active) {
            return false;
        }
        double distanceX = Math.abs(x - Target.getX());
        double distanceY = Math.abs(y - Target.getY());

        return distanceX < 25 && distanceY < 25;

    }


}


