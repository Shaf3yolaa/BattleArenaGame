package com.battlearena.battlearenagame.models;

import javafx.scene.paint.Color;

public class Warrior extends Fighter {

    public Warrior(double startX, double startY) {
        super(10, 10, 100, 3.0, new Pistol());
        this.view.setFill(Color.BLACK);
    }

}