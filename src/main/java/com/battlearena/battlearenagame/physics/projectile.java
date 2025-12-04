package com.battlearena.battlearenagame.physics;
import javafx.scene.shape.Rectangle;

public class projectile {
    private double xLimtL;
    private  double xLimtR;
    private  double x;
    private double y;
    private double speed;

    private double xShoot;
    private double yShoot;

    public boolean active = true;
    private Rectangle view;
    private boolean movingRight;


    public projectile(double xLimt,double x,double y, double xLimtR, double speed,
                      double xShoot,double yShoot  ,  boolean movingRight) {
        this.x = x;
        this.y = y;
        //complete
        this.xLimtL = 200;
        this.xLimtR = 500;

        this.speed = speed;
        this.xShoot = xShoot;
        this.yShoot = 5;
        this.view = new Rectangle(xShoot, yShoot);
        this.movingRight = movingRight;

    }

    public double getX() {
        return x;
    }

    public double getSpeed() {
        return speed;
    }

    public Rectangle getView() {
        return view;
    }
}


