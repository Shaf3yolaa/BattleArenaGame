package com.battlearena.battlearenagame.physics;

public class Move {

    private double x;
    private boolean active = true;
    private boolean movingRight;

    private double minX ;
    private double maxX;
    private double mid ;


    public Projectile(double startX, boolean movingRight,double minX, double maxX, double mid) {
        this.x = startX;
        this.movingRight = movingRight;
        this.minX = minX;
        this.maxX = maxX;
        this.mid = mid;
    }


    public void move() {
        if (!active) return;

        if (movingRight) {
            x++;
        } else {
            x--;
        }
    }


    public void moveSteps(int steps) {
        if (!active) return;

        if (movingRight) {
            x += steps;
        } else {
            x -= steps;
        }
    }


    public void limit() {
        if (!active) return;


        if (x < minX || x > maxX) {
            active = false;
        }
    }


    public void limitWithMid() {
        if (!active) return;


        if (movingRight && x >= mid) {
            active = false;
        }


        if (!movingRight && x <= mid) {
            active = false;
        }


            active = false;
        }
    }


    public boolean reachedMid() {
        if (movingRight) {
            return x >= mid;
        } else {
            return x <= mid;
        }
    }


    public double distanceToMid() {
        return Math.abs(x - mid);
    }



}
