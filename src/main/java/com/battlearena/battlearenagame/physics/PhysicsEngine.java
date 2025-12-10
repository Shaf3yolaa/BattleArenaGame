package com.battlearena.battlearenagame.physics;

import com.battlearena.battlearenagame.models.Fighter;

public class PhysicsEngine {
    private double w;
    private double h;


    public PhysicsEngine(double w, double h) {

        this.w = w;
        this.h = h;
    }


    public void movePlayer1(Fighter p1, boolean up, boolean down, boolean left, boolean right) {
        double nextX = p1.getX();
        double nextY = p1.getY();
        double speed = p1.getSpeed();
        double p1Width  = p1.getView().getWidth();
        double p1Height = p1.getView().getHeight();

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
        if (nextX > (w/2)-p1Width)
            nextX = (w/2)-p1Width;
        if (nextY < 0)
            nextY = 0;
        if (nextY > h-p1Height)
            nextY = h-p1Height;

        p1.setX(nextX);
        p1.setY(nextY);
    }

    public void movePlayer2(Fighter p2, boolean up, boolean down, boolean left, boolean right) {
        double nextX = p2.getX();
        double nextY = p2.getY();
        double speed = p2.getSpeed();
        double p2Width  = p2.getView().getWidth();
        double p2Height = p2.getView().getHeight();

        if (up)
            nextY -= speed;
        if (down)
            nextY += speed;
        if (left)
            nextX -= speed;
        if (right)
            nextX += speed;


        if (nextX < w/2)
            nextX = w/2;
        if (nextX > w-p2Width)
            nextX = w-p2Width;
        if (nextY < 0)
            nextY = 0;
        if (nextY > h-p2Height)
            nextY = h-p2Height;

        p2.setX(nextX);
        p2.setY(nextY);
    }

    public boolean checkCollision(Projectile bullet, Fighter target) {
        if (!bullet.active) return false;

        double bulletX = bullet.getX();
        double bulletY = bullet.getY();

        double targetX = target.getX();
        double targetY = target.getY();
        double targetW = target.getView().getWidth();
        double targetH = target.getView().getHeight();
        return (bulletX >= targetX && bulletX <= targetX + targetW) && (bulletY >= targetY && bulletY <= targetY + targetH);
    }

}
