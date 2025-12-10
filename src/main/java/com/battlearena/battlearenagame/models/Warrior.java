package com.battlearena.battlearenagame.models;

import javafx.scene.paint.Color;

public class Warrior extends Fighter {

    private static final String Defaultname = "Warrior";

    public Warrior(double startX, double startY) {
        super(Defaultname, startX, startY, 100, 5, new Pistol());
        this.view.setFill(Color.YELLOW);
    }

    @Override
    public void addWeapon(Weapon w) {
        super.addWeapon(w);
    }

    @Override
    public void cycleWeapons() {
        super.cycleWeapons();
    }

    @Override
    public void shoot() {
        long now = System.currentTimeMillis();
        if (canShoot(now)) {
            System.out.println(this.getName() + " fired a shot!");
        }
    }

}