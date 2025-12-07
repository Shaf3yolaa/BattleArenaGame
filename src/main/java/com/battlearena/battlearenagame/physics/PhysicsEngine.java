package com.battlearena.battlearenagame.physics;

import com.battlearena.battlearenagame.models.Fighter;

public class PhysicsEngine {
    private double w;
    private double h;
    public boolean active = true;


    public PhysicsEngine(double w, double h) {

        this.w = w;
        this.h = h;
    }


    public void movePlayer1(Fighter p1, boolean up, boolean down, boolean left, boolean right) {
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

    public void movePlayer2(Fighter p2, boolean up, boolean down, boolean left, boolean right) {
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


        if (nextX < w / 2)
            nextX = w / 2;
        if (nextX > w - 40)
            nextX = w - 40;
        if (nextY < 0)
            nextY = 0;
        if (nextY > h - 40)
            nextY = h - 40;

        p2.setX(nextX);
        p2.setY(nextY);
    }

    public boolean checkCollision(Projectile bullet, Fighter target) {

        if (!bullet.active) {
            return false;
        }

        double distanceX = Math.abs(bullet.getX() - target.getX());
        double distanceY = Math.abs(bullet.getY() - target.getY());

        return distanceX < 25 && distanceY < 25;
    }
}
