package com.battlearena.battlearenagame.model;

public class PhysicsEngine extends Player {
private double w;
private double h;

//class player
private double player1;
    private double X;
    private double Y;
    private double speed;


    public PhysicsEngine(double w,double h,Player X ,Player Y , Player speed) {
        super(speed,Y,X);
        this.w = w;
        this.h = h;
    }

}
    public void movePlayer1(Player p1, boolean up, boolean down, boolean left, boolean right) {
        double nextX = p1.getX();
        double nextY = p1.getY();
        double speed = p1.getSpeed();

        if (up)
            nextY -= speed;
        if (down)
            nextY += speed;
        if (left)
            nextX -= speed;
        if (right)
            nextX += speed;

        if (nextX < 0)
            nextX = 0;
        if (nextX > (w / 2) - 40)
            nextX = (w / 2) - 40;
        if (nextY < 0)
            nextY = 0;
        if (nextY > h - 40)
            nextY = h - 40;

        p1.setX(nextX);
        p1.setY(nextY);
    }

public void movePlayer2(Player p2, boolean up, boolean down, boolean left, boolean right) {
    double nextX = p2.getX();
    double nextY = p2.getY();
    double speed = p2.getSpeed();

    if (up)
        nextY -= speed;
    if (down)
        nextY += speed;
    if (left)
        nextX -= speed;
    if (right)
        nextX += speed;


    if (nextX < width / 2)
        nextX = width / 2;
    if (nextX > width - 40)
        nextX = width - 40;
    if (nextY < 0)
        nextY = 0;
    if (nextY > height - 40)
        nextY = height - 40;

    p2.setX(nextX);
    p2.setY(nextY);
}
