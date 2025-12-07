package com.battlearena.battlearenagame.models;

import javafx.scene.paint.Color;

public class Archer extends Fighter {

    private static final String Defaultname = "Archer";

    public Archer(double startX, double startY) {
        super(Defaultname, startX, startY, 100, 5.0, new Bow());
        this.view.setFill(Color.PURPLE);
    }
    @Override
    public void shoot() {
        long now = System.currentTimeMillis();

        if (canShoot(now)) {
            System.out.println(this.getName() + " fired an arrow!");

        }
    }
}