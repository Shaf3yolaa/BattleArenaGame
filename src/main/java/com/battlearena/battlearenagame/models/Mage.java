package com.battlearena.battlearenagame.models;

import javafx.scene.paint.Color;

public class Mage extends Fighter {

    public Mage(double startX, double startY) {
        super(20, 20, 100, 4.0, new MagicWand());
        this.view.setFill(Color.BLUE);
    }



}