package com.battlearena.battlearenagame.model;

import javafx.scene.paint.Color;

public class Archer extends Fighter {

    public Archer(double startX, double startY) {
        super(30, 30, 100, 5.0, new Bow());
        this.view.setFill(Color.PURPLE);
    }

}