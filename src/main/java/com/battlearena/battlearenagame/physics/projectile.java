package com.battlearena.battlearenagame.physics;
import javafx.scene.shape.Rectangle;

public class projectile {
    private double xLimt;
    private  double yLimt;
    private  double x;
    private double y;
    private double speedPlayer;
    private double speedwepain;
    private double xplayer;
    private double yplayer;
    private double xShoot;
    private double yShoot;
    public projectile(double xLimt,double x,double y, double yLimt, double speedwepain, double speedPlayer,
                      double xplayer,double yplayer,double xShoot,double yShoot) {
        this.x = x;
        this.y = y;
        this.yLimt = 200;
        this.xLimt = 500;
        this.speedPlayer = speedPlayer;
        this.speedwepain = speedwepain;
        this.xplayer = xplayer;
        this.yplayer = 10;
        this.xShoot = xShoot;
        this.yShoot = 5;
    }

    public double getXplayer() {
        return xplayer;
    }

    public double getYplayer() {
        return yplayer;
    }

    public Rectangle PlayerBox(double xplayer,double yplayer){
        Rectangle r= new Rectangle();
        r.setWidth(xplayer);
        r.setHeight(yplayer);
        return r ;
    }
    public Rectangle ShootBox(double xShoot,double yShoot){
        Rectangle r= new Rectangle();
        r.setWidth(xShoot);
        r.setHeight(yShoot);
        return r ;
    }

}


