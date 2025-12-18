package com.battlearena.battlearenagame.models;

import javafx.scene.paint.Color;

public class Archer extends Fighter {

    private static final String Defaultname = "Archer";

    public Archer(double startX, double startY) {
        super(Defaultname, startX, startY, 100, 5.0, new Bow());
        this.view.setFill(Color.DEEPPINK);
    }
    @Override
    public void shoot() {
        System.out.println("Archer shoots an arrow!");

    }

}