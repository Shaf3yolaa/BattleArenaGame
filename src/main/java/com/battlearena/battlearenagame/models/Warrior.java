package com.battlearena.battlearenagame.models;

import javafx.scene.paint.Color;

public class Warrior extends Fighter {

    private static final String Defaultname = "Warrior";

    public Warrior(double startX, double startY) {
        super(Defaultname, startX, startY, 100, 5, new Pistol());
        this.view.setFill(Color.BLACK);
    }

    @Override
    public void shoot() {
        long now = System.currentTimeMillis();

    }

}