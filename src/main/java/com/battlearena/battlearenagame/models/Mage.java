package com.battlearena.battlearenagame.models;

import javafx.scene.paint.Color;

public class Mage extends Fighter {

    private static final String Defaultname = "Mage";

    public Mage(double startX, double startY) {

        super(Defaultname, startX, startY, 100, 4.0, new MagicWand());
        this.view.setFill(Color.BLUE);
    }
    @Override
    public void shoot() {
        long now = System.currentTimeMillis();


        if (canShoot(now)) {
            System.out.println(this.getName() + " cast a spell!");
        }
    }

    @Override
    public void addWeapon(Weapon w) {
        super.addWeapon(w);
    }

    @Override
    public void cycleWeapons() {
        super.cycleWeapons();
    }
}