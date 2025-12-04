package com.battlearena.battlearenagame.model;

public class PhysicsEngine {
private double w;
private double h;

//class player
private double player1;
    private double X;
    private double Y;

public PhysicsEngine(double w,double h){
    this.w=w;
    this.h=h;


}
    public void movePlayer1(double player1, boolean up, boolean down, boolean left, boolean right) {
        double nextX = player1.getX();
        double nextY = player1.getY();
        double speed = player1.getSpeed();

        if (up) nextY -= speed;
        if (down) nextY += speed;
        if (left) nextX -= speed;
        if (right) nextX += speed;
        if (nextX > (w / 2) - 40) nextX = (w / 2) - 40; // Stop at middle line [cite: 25]
        if (nextY < 0) nextY = 0;
        if (nextY > h - 40) nextY = h - 40;

}


}
