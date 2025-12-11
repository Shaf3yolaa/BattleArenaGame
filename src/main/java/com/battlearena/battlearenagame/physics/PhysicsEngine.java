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
        movePlayer(p1, up, down, left, right);
    }

    public void movePlayer2(Fighter p2, boolean up, boolean down, boolean left, boolean right) {
        movePlayer(p2, up, down, left, right);
    }

    private void movePlayer(Fighter p, boolean up, boolean down, boolean left, boolean right) {
        double nextX = p.getX();
        double nextY = p.getY();
        double speed = p.getSpeed();
        double pWidth  = p.getView().getWidth();
        double pHeight = p.getView().getHeight();







        if (up)
            nextY -= speed;
        if (down)
            nextY += speed;
        if (left)
            nextX -= speed;
        if (right)
            nextX += speed;

        if (up) p.setRotation(270);
        if (down) p.setRotation(90);
        if (left) p.setRotation(180);
        if (right) p.setRotation(0);


        if (up && right) p.setRotation(315);
        if (up && left) p.setRotation(225);
        if (down && right) p.setRotation(45);
        if (down && left) p.setRotation(135);


        if (nextX < 0)
            nextX = 0;
        if (nextX > w-pWidth)
            nextX = w-pWidth;
        if (nextY < 0)
            nextY = 0;
        if (nextY > h-pHeight)
            nextY = h-pHeight;

        p.setX(nextX);
        p.setY(nextY);
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
